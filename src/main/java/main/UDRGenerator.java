package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.Call;
import main.model.Subscriber;
import org.aspectj.util.FileUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс генерации UDR отчетов.
 */
public class UDRGenerator {

    private Map<Integer, List<Call>> callsPerMonth;
    private List<Subscriber> subscriberList;

    /**
     * Instantiates a new Udr generator.
     *
     * @param callsPerMonth  map со списком звонков за каждый месяц
     * @param subscriberList список абонентов
     */
    public UDRGenerator(Map<Integer, List<Call>> callsPerMonth, List<Subscriber> subscriberList) {
        this.callsPerMonth = callsPerMonth;
        this.subscriberList = subscriberList;
    }

    private final String PATH = "reports/";

    /**
     * Метод  сохраняет все отчеты и выводит в консоль таблицу
     * со всеми абонентами и итоговым временем звонков по всему
     * тарифицируемому периоду каждого абонента
     */
    public void generateReport() {
        FileUtil.deleteContents(new File(PATH));
        new File("reports").mkdir();
        Map<Subscriber, TotalTime> subsTotalTimeMap = new HashMap<>();
        subscriberList.forEach(subscriber -> subsTotalTimeMap.put(subscriber, new TotalTime()));
        ObjectMapper mapper = new ObjectMapper();
        for (Integer month : callsPerMonth.keySet()) {
            List<Call> calls = callsPerMonth.get(month);
            Map<Subscriber, List<Call>> groupedCallsBySub = calls.stream()
                    .collect(Collectors.groupingBy(Call::getSubscriber));
            for (Subscriber subscriber : groupedCallsBySub.keySet()) {
                TotalTime timeForEachSub = new TotalTime();
                List<Call> callListForEachSub = groupedCallsBySub.get(subscriber);
                callListForEachSub.forEach(timeForEachSub::timeCollector);
                callListForEachSub.forEach(call -> subsTotalTimeMap.get(subscriber).timeCollector(call));
                timeForEachSub.convertTime();
                try {
                    FileWriter writer = new FileWriter(PATH + subscriber.getNumber() + "_" + month + ".json");
                    mapper.writeValue(writer, timeForEachSub);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Subscriber s : subsTotalTimeMap.keySet()) {
            try {
                TotalTime time = subsTotalTimeMap.get(s);
                time.convertTime();
                System.out.println(mapper.writeValueAsString(subsTotalTimeMap.get(s)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * сохраняет все отчеты и выводит в консоль таблицу
     * по одному абоненту и его итоговому времени звонков в каждом месяце
     *
     * @param msisdn указанный абонент
     */
//
    public void generateReport(Subscriber msisdn) {

        FileUtil.deleteContents(new File(PATH));
        new File("reports").mkdir();
        Map<Subscriber, TotalTime> subsTotalTimeMap = new HashMap<>();
        subscriberList.forEach(subscriber -> subsTotalTimeMap.put(subscriber, new TotalTime()));
        ObjectMapper mapper = new ObjectMapper();
        for (Integer month : callsPerMonth.keySet()) {
            List<Call> calls = callsPerMonth.get(month);
            Map<Subscriber, List<Call>> groupedCallsBySub = calls.stream()
                    .collect(Collectors.groupingBy(Call::getSubscriber));
            for (Subscriber subscriber : groupedCallsBySub.keySet()) {
                TotalTime timeForEachSub = new TotalTime();
                List<Call> callListForEachSub = groupedCallsBySub.get(subscriber);
                callListForEachSub.forEach(timeForEachSub::timeCollector);
                callListForEachSub.forEach(call -> subsTotalTimeMap.get(subscriber).timeCollector(call));
                timeForEachSub.convertTime();
                try {
                    FileWriter writer = new FileWriter(PATH + subscriber.getNumber() + "_" + month + ".json");
                    mapper.writeValue(writer, timeForEachSub);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            for (Subscriber subscriber : subsTotalTimeMap.keySet()) {
                if (subscriber.getNumber().equals(msisdn.getNumber())) {
                    TotalTime time = subsTotalTimeMap.get(subscriber);
                    time.convertTime();
                    System.out.println(mapper.writeValueAsString(subsTotalTimeMap.get(subscriber)));
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * сохраняет отчет и выводит в консоль таблицу по одному
     * абоненту и его итоговому времени звонков в указанном месяце.
     *
     * @param msisdn абонент
     * @param month  номер месяца
     */
    public void generateReport(Subscriber msisdn, int month) {
        FileUtil.deleteContents(new File(PATH));
        new File("reports").mkdir();
        ObjectMapper mapper = new ObjectMapper();
        List<Call> calls = callsPerMonth.get(month);
        TotalTime timeForEachSub = new TotalTime();
        Map<Subscriber, List<Call>> groupedCallsBySub = calls.stream()
                .collect(Collectors.groupingBy(Call::getSubscriber));
        for (Subscriber subscriber : groupedCallsBySub.keySet()) {
            if (subscriber.getNumber().equals(msisdn.getNumber())) {
                List<Call> callListForEachSub = groupedCallsBySub.get(subscriber);
                callListForEachSub.forEach(timeForEachSub::timeCollector);
                timeForEachSub.convertTime();
                try {
                    FileWriter writer = new FileWriter(PATH + subscriber.getNumber() + "_" + month + ".json");
                    mapper.writeValue(writer, timeForEachSub);
                    System.out.println(mapper.writeValueAsString(timeForEachSub));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


