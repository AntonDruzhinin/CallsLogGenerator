package main.model;

import main.TotalTime;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UDRGenerator {

    private Map<Integer, List<Call>> callsPerMonth;

    public UDRGenerator(Map<Integer, List<Call>> callsPerMonth) {
        this.callsPerMonth = callsPerMonth;
    }

    private final String PATH = "reports";
    public void generateReport(){
        for (Integer month : callsPerMonth.keySet()){
            List<Call> calls = callsPerMonth.get(month);
            Map<Subscriber, TotalTime> totalTimePerSub = new HashMap<>();
            for (Subscriber subscriber : totalTimePerSub.keySet()){

                try {
                    FileWriter writer = new FileWriter(PATH  + subscriber.getNumber() + "_" + month + ".json");
                    writer.write(new String());
                    writer.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }






        }





    }
//
//    public void generateReport(Subscriber msisdn){
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
