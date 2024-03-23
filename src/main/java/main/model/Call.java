package main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "CDR")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long number;
    @Column(name = "start_time")
    private Timestamp startCallTime;
    @Column(name = "finish_time")
    private Timestamp endCallTime;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "enum('INCOMINGCALL', 'OUTCOMMINGCALL')", name = "call_type")
    private CallType callType;

    public Call getCall(Abonent abonent){
        Call call = new Call();
        return call;
    }
}
