package main;

import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CDRParser {
    private String path;

    public CDRParser(String path) {
        this.path = path;

    }

    private Map<Integer, List<String>> filesMap = new HashMap<>();
    private void parseCRDfile() throws IOException {
        File[] files =new File(path).listFiles();
        for(File file : files){

            int month = Integer.parseInt(file.getName().split("\\.")[0]);
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            filesMap.put(month, lines);
        }

    }

    public Map<Integer, List <Call>> getCallMap(){
        try {
            parseCRDfile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, List <Call>> readCallList= new HashMap<>();
        for (Integer month : filesMap.keySet()){
            List<Call> callList = new ArrayList<>();
            List<String> lines = filesMap.get(month);

            lines.forEach(line -> {
                    String[] nodes = line.split(", ");
                    Call call = new Call();
                    call.setCallType(CallType.values()[Integer.parseInt(nodes[0])-1]);
                    call.setSubscriber(new Subscriber(nodes[1]));
                    call.setStartCallTime(Long.parseLong(nodes[2]));
                    call.setEndCallTime(Long.parseLong(nodes[3]));
                    callList.add(call);

            });
            readCallList.put(month, callList);

        }
        return readCallList;
    }

}
//    public static void main(String[] args) {
//        // Загрузка CDR файла
//        String cdrFile = "/path/to/CDR_file.txt";
//        List<CdrRecord> cdrRecords = parseCdrFile(cdrFile);
//
//        // Формирование UDR отчета
//        StringBuilder udrReport = new StringBuilder();
//        for (CdrRecord cdrRecord : cdrRecords) {
//            String udrLine = generateUdrLine(cdrRecord);
//            udrReport.append(udrLine).append("\n");
//        }
//
//        System.out.println("UDR Report:");
//        System.out.println(udrReport);
//    }
//
//    // Функция для парсинга CDR файла и создания списка CdrRecord объектов
//    private static List<CdrRecord> parseCdrFile(String cdrFilePath) {
//        List<CdrRecord> result = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(cdrFilePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                CdrRecord cdrRecord = parseCdrRecord(line);
//                if (cdrRecord != null) {
//                    result.add(cdrRecord); // добавление CdrRecord в список
//                }
//}
//
//            e.printStackTrace();
//        }
//        return result;
//    }
//    private static CdrRecord parseCdrRecord(String cdrLine) {
//
//        if (cdrLine == null || cdrLine.isEmpty()) {
//            return null;
//        }
//
//        String[] data = cdrLine.split(SEPARATOR);
//        if (data.length == 4 && validateData(data)) {
//            int callType = Integer.parseInt(data[0]);
//            if (callType == 01 || callType == 02) {
//                String phoneNumber = data[1];
//                long startTime = Long.parseLong(data[2]);
//                long endTime = Long.
