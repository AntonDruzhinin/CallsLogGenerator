package main;

import main.model.Call;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Класс для записи звоков в CDR.txt по месяцам
 */
public class CDR {
    public void CDRToFile(List<Call> callList, String path) {

        Map<Integer, List<Call>> groupedCallsByMonth = callList.stream()
                .collect(Collectors.groupingBy(Call::numOfMonth));

        groupedCallsByMonth.forEach((month, callLogList) -> {
            StringBuilder stringBuilder = new StringBuilder();
            callLogList.forEach(call -> stringBuilder.append(call.stringToCDR()));

            try {
                FileWriter writer = new FileWriter(path + month + ".txt");
                writer.write(stringBuilder.toString());
                writer.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


}






