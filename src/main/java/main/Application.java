package main;

import main.repositories.CallRepository;
import main.repositories.SubscriberRepository;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
//    @Autowired
//    SubscriberRepository subscriberRepository;
//    @Autowired
//    CallRepository callRepository;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);

    }

    @Override
    public void run(String... arg0) throws Exception {
        //new DefaultController(subscriberRepository, callRepository);

    }
}
