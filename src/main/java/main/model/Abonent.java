package main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.Random;
@Entity
@Table(name = "abonent")
public class Abonent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    long number;


    public long getNumber() {
        Random rn = new Random();
        long number= rn.nextLong(79999999999L - 79000000000L  + 1) + 79000000000L;
        return number;
    }

}
