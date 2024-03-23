package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    public  List<Long> generateAbonentList(int numOfAbonents){
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
