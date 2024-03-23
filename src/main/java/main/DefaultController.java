package main;

import main.model.Abonent;
import main.repositories.AbonentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class DefaultController {
    private static final String path = "/reports";
    private static final Date STARTSOFREPORT = new Date(2023, 03, 20);
    private static final Date FINISHOFREPORT = new Date(2024, 03, 20);

    @Autowired
    AbonentRepository abonentRepository;

    public void run(){
        Generator generator = new Generator();

        List<Long> abonentNumbers =generator.generateAbonentList(10);
        List<Abonent> abonents = new ArrayList<>();
        Abonent abonent = new Abonent();
        abonent.setNumber("79657708283");
        abonentRepository.save(abonent);

        System.out.println("Spring boot standalone application is working...");
    }


}
