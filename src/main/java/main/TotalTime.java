package main;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.Call;
import main.model.CallType;
import java.time.*;


@Getter
@Setter
@NoArgsConstructor
public class TotalTime {

    private String msisdn = "";
    @JsonFormat(pattern = "hhh.mm.ss")
    private LocalTime incomingCallTime;
    private LocalTime outcomingCallTime;

    @JsonIgnore
    private  long incoming;
    @JsonIgnore
    private transient long outcoming;


//    public TotalTime(Long incoming, Long outcoming) {
//        this.incoming = incoming;
//        this.outcoming = outcoming;
//    }
    private void addInc(long callTime){
        incoming+=callTime;
    }
    private void addOutc(long callTime){
        outcoming += callTime;
    }

    public void timeCollector(Call call){
        if (msisdn.equals("")){
            msisdn = call.getSubscriber().getNumber();
        }
        if(call.getCallType().equals(CallType.INCOMINGCALL)){
            addInc(call.getCallTime());
        }
        if(call.getCallType().equals(CallType.OUTCOMMINGCALL)){
            addOutc(call.getCallTime());
        }
    }
    public void convertTime(){

        Instant instantInc = Instant.ofEpochSecond(incoming);
        Instant instantOutc = Instant.ofEpochSecond(outcoming);
        incomingCallTime =LocalTime.ofInstant(instantInc, ZoneId.of("+00:00:00"));
        outcomingCallTime = LocalTime.ofInstant(instantOutc, ZoneId.of("+00:00:00"));
    }

}
