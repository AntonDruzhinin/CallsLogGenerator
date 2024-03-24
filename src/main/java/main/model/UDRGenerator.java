package main.model;

import main.TotalTime;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
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
            Map<Subscriber, TotalTime> incTotalTime;
            Map<Subscriber, >

        }



        try {
            FileWriter writer = new FileWriter(PATH  + Subscriber + "_" + month + ".json");
            writer.write(stringBuilder.toString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
