package main;

import main.model.Call;
import main.model.CallType;
import main.model.Subscriber;
import main.model.UDRGenerator;
import main.repositories.CallRepository;
import main.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultController {
    private static final String path = "src/main/resources/";
    private static final LocalDate STARTSOFREPORT =LocalDate.of(2023, 01, 01);
    private static final LocalDate FINISHOFREPORT =LocalDate.of(2024, 01, 01);

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

        List<Call> callList = generator.generateCallList(readSubsrciberList, STARTSOFREPORT, FINISHOFREPORT);

        callRepository.saveAll(callList);
        System.out.println(callList.size());

        CDR cdr = new CDR();
        cdr.CDRToFile(callList, path);

        CDRParser cdrParser = new CDRParser(path);
        Map<Integer, List <Call>> readCallMap= cdrParser.getCallMap();

        UDRGenerator udrGenerator  = new UDRGenerator(readCallMap);
        udrGenerator.generateReport();






    }


}
