package main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class CallsLog {
    final String path = "/reports";
    final Date STARTSOFREPORT = new Date(2023, 03, 20);
    final Date FINISHOFREPORT = new Date(2024, 03, 20);
    public static void main(String[] args) {
        List<Long> abonents = generateAbonentList(10);



    }

     public static List<Long> generateAbonentList(int numOfAbonents){
        List<Long> abonentList = new ArrayList<>();
        for(int i = 0; i <= numOfAbonents; i++){
            Random rn = new Random();
            long number= rn.nextLong(79999999999L - 79000000000L  + 1) + 79000000000L;
            abonentList.add(number);
            System.out.println(number);
        }
        return abonentList;
    }
}
