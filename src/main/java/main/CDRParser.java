package main;

import lombok.Getter;
import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * Класс для чтения файлов CDR.txt
 */
@Getter
public class CDRParser {
    private String path;
    private List<Subscriber> subscriberList = new ArrayList<>();

    /**
     * Instantiates a new Cdr parser.
     *
     * @param path путь к директории с файлами
     */
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

    /**
     * Метод для получения map с ключами в виде номера месяца и значениями списков вызовов
     *
     * @return the map
     */
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
                    Subscriber subscriber = new Subscriber("");
                    for (Subscriber s:subscriberList){
                        if(s.getNumber().equals(nodes[1])){
                            subscriber = s;
                        }

                    }
                    if(subscriber.getNumber().equals("")){
                        subscriber = new Subscriber(nodes[1]);
                        subscriberList.add(subscriber);
                    }

                    call.setSubscriber(subscriber);
                    call.setStartCallTime(Long.parseLong(nodes[2]));
                    call.setEndCallTime(Long.parseLong(nodes[3]));
                    callList.add(call);

            });
            readCallList.put(month, callList);

        }
        return readCallList;
    }

}
