package main;

import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private final long maxCallTime = 500000;
    public  List<Subscriber> generateSubscriberList(int numOfSubscribers){
        List<Subscriber> subscriberList = new ArrayList<>();
        for(int i = 0; i < numOfSubscribers; i++){
            Long number= new Random().nextLong(79999999999L - 79000000000L  + 1) + 79000000000L;
            subscriberList.add(new Subscriber(number.toString()));
        }
        return subscriberList;
    }

    public List<Call> generateCallList(List<Subscriber> subscriberList,
                                       int numOfCalls,
                                       Date startsOfReport,
                                       Date finishOfReport){

        List<Call> callList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numOfCalls; i++){
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

    public long randomBetweenDates(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        return  ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
    }

    public long randomCallsEnd(long callsStartTime){
        return new Random().nextLong(maxCallTime)+ callsStartTime;
    }
}
