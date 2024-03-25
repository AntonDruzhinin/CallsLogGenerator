package main.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

/**
 * Класс абонента
 */
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

    /**
     * Instantiates a new Subscriber.
     *
     * @param number -номер абонента
     */
    public Subscriber(String number) {
        this.number = number;
    }


}
