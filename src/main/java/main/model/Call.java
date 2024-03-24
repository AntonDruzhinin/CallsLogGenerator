package main.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import main.repositories.CallRepository;

import java.io.Serializable;
import java.util.Arrays;

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


}
