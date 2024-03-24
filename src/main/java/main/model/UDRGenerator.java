package main.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.TotalTime;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UDRGenerator {

    private Map<Integer, List<Call>> callsPerMonth;

    public UDRGenerator(Map<Integer, List<Call>> callsPerMonth) {
        this.callsPerMonth = callsPerMonth;
    }

    private final String PATH = "reports/";
    public void generateReport(){
        new File("reports").mkdir();
        for (Integer month : callsPerMonth.keySet()){
            TotalTime timeForEachSub  = new TotalTime();
            List<Call> calls = callsPerMonth.get(month);
            Map<Subscriber, List<Call>> groupedCallsBySub = calls.stream()
                    .collect(Collectors.groupingBy(Call::getSubscriber));

            for (Subscriber subscriber : groupedCallsBySub.keySet()){

                List<Call> callListForEachSub = groupedCallsBySub.get(subscriber);
                callListForEachSub.forEach(c -> timeForEachSub.timeCollector(c));
                timeForEachSub.convertTime();

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(timeForEachSub));
                    FileWriter writer = new FileWriter(PATH  + subscriber.getNumber() + "_" + month + ".json");
                    mapper.writeValue(writer, timeForEachSub);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }





    }


//
    public void generateReport(Subscriber msisdn){
        new File("reports").mkdir();
        for (Integer month : callsPerMonth.keySet()) {
            List<Call> calls = callsPerMonth.get(month);
            Map<Subscriber, List<Call>> groupedCallsBySub = calls.stream()
                    .collect(Collectors.groupingBy(Call::getSubscriber));


            for (Subscriber subscriber : groupedCallsBySub.keySet()) {
                TotalTime timeForEachSub = new TotalTime();
                List<Call> callListForEachSub = groupedCallsBySub.get(subscriber);
                callListForEachSub.forEach(c -> timeForEachSub.timeCollector(c));
                timeForEachSub.convertTime();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    if(subscriber.equals(msisdn)) {
                        System.out.println(mapper.writeValueAsString(timeForEachSub));
                    }
                    FileWriter writer = new FileWriter(PATH + subscriber.getNumber() + "_" + month + ".json");
                    mapper.writeValue(writer, timeForEachSub);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
//
//    public void generateReport(Subscriber msisdn,int month){
//
//
//
//        try {
//            FileWriter writer = new FileWriter(PATH  + month + ".txt");
//            writer.write(stringBuilder.toString());
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
