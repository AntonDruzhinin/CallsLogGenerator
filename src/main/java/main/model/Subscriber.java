package main.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
@Table(name = "subscribers")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
    private int id;
    @Column(name = "number")
    private String number;

    public Subscriber(String number) {
        this.number = number;
    }
//
//    @OneToMany(mappedBy = "subscriber",cascade = CascadeType.ALL, orphanRemoval = true)
//
//    private List<Call> calls = new ArrayList<>();

}
