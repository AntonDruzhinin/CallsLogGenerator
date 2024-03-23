package main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Random;
@Entity
@Table(name = "abonent")
@Data
public class Abonent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
    int id;
    @Column(name = "number")
    String number;


    public long getNumber() {
        Random rn = new Random();
        long number= rn.nextLong(79999999999L - 79000000000L  + 1) + 79000000000L;
        return number;
    }

}
