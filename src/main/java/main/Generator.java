package main;

import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для генерации абонентов и вызовов
 */
public class Generator {
    private final long MAXCALLTIME = 5000;
    private final int MAXNUMOFCALLS = 1000000;

    /**
     * Генерирует список абонентов
     *
     * @param numOfSubscribers колличество абонентов
     * @return the list
     */
    public  List<Subscriber> generateSubscriberList(int numOfSubscribers){
        List<Subscriber> subscriberList = new ArrayList<>();
        for(int i = 0; i < numOfSubscribers; i++){
            Long number= new Random().nextLong(79999999999L - 79000000000L  + 1) + 79000000000L;
            subscriberList.add(new Subscriber(number.toString()));
        }
        return subscriberList;
    }


    /**
     * Генерирует список звонков.
     *
     * @param subscriberList список абонентов
     * @param startsOfReport начало отчетного периода
     * @param finishOfReport конец периода
     * @return the list
     */
    public List<Call> generateCallList(List<Subscriber> subscriberList,
                                       LocalDate startsOfReport,
                                       LocalDate finishOfReport){

        List<Call> callList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(MAXNUMOFCALLS); i++){
            Call call = new Call();
            call.setCallType(CallType.values()[random.nextInt(CallType.values().length)]);

            call.setSubscriber(subscriberList.get(random.nextInt(subscriberList.size())));
            long callsStart = randomBetweenDates(startsOfReport, finishOfReport);
            call.setStartCallTime(callsStart);
            call.setEndCallTime(randomCallsEnd(callsStart));
            callList.add(call);
        }
        return callList;
    }

    /**
     * Метод используется для генерации времени начала вызова в определенном периоде
     *
     * @param startInclusive дата начала периода
     * @param endExclusive   дата конца периода
     * @return the long
     */
    public long randomBetweenDates(LocalDate startInclusive, LocalDate endExclusive) {
        long startMillis = startInclusive.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        long endMillis = endExclusive.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        return  ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
    }

    /**
     * Метод для получения случайного времени окончания звонка.
     *
     * @param callsStartTime время начала вызова
     * @return the long
     */
    public long randomCallsEnd(long callsStartTime){
        return new Random().nextLong(MAXCALLTIME)+ callsStartTime;

    }




}
