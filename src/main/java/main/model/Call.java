package main.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import main.repositories.CallRepository;

import java.io.Serializable;
import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CDR")
public class Call implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    private String number;
    @Column(name = "start_time")
    private Long startCallTime;
    @Column(name = "finish_time")
    private Long endCallTime;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('INCOMINGCALL', 'OUTCOMMINGCALL')", name = "call_type")
    private CallType callType;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Subscriber subscriber;

    public String stringToCDR(){
        StringBuilder str = new StringBuilder("0");
        str.append(Integer.toString(callType.ordinal()+1) + ", ");
        str.append(subscriber.getNumber() + ", ");
        str.append(startCallTime.toString() + ", ");
        str.append(endCallTime.toString() + "\n");
        return str.toString();
    }

    public int numOfMonth(){
        Instant instant = Instant.ofEpochSecond(startCallTime);
        int monthNum = LocalDate.ofInstant(instant,ZoneId.systemDefault()).getMonthValue();
        return monthNum;
    }

    public LocalTime getCallTime(){
        Instant instant = Instant.ofEpochSecond(startCallTime - endCallTime);
        LocalTime localTime = LocalTime.ofInstant(instant, ZoneId.systemDefault());
        return localTime;
    }

}
