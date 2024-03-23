package main;

import main.model.Abonent;
import main.repositories.AbonentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);

    }

    @Override
    public void run(String... arg0) throws Exception {
        new DefaultController().run();

    }
}
