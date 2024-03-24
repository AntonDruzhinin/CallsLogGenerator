package main;

import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;
import main.repositories.CallRepository;
import main.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DefaultController {
    private static final String path = "/reports";
    private static final Date STARTSOFREPORT = new Date(2023, 03, 20);
    private static final Date FINISHOFREPORT = new Date(2024, 03, 20);
    private static final int maxNumOfCallsPerMonth = 100;
    private static final int numOfSubscribers = 15;
    @Autowired
    private final SubscriberRepository subscriberRepository;
    @Autowired
    private final CallRepository callRepository;

    public DefaultController(SubscriberRepository subscriberRepository, CallRepository callRepository) {
        this.subscriberRepository = subscriberRepository;
        this.callRepository = callRepository;
        run();
    }

    public void run(){

        Generator generator = new Generator();
        List<Subscriber> subscribers =generator.generateSubscriberList(numOfSubscribers);
        subscriberRepository.saveAll(subscribers);
        System.out.println("Subscribers add in DataBase: " + subscriberRepository.count());
        List<Subscriber> readSubsrciberList = new ArrayList<>();
        subscriberRepository.findAll().forEach(a -> readSubsrciberList.add(a));
        List<Call> callList = generator.generateCallList(readSubsrciberList, maxNumOfCallsPerMonth, STARTSOFREPORT, FINISHOFREPORT);



        callRepository.saveAll(callList);
        System.out.println(callList.get(15).getCallType());




    }


}
