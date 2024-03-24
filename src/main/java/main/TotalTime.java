package main;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;

@Getter
@Setter
public class TotalTime {
    private LocalTime incoming;
    private LocalTime outcoming;

    public TotalTime(LocalTime incoming, LocalTime outcoming) {
        this.incoming = incoming;
        this.outcoming = outcoming;
    }
    public void addInc(LocalTime time){
        Date date = new Date(2024, 01, 04);
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.);
        date.add
        incoming.add;
    }
}
