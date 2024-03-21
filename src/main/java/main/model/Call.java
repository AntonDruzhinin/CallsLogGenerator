package main.model;

import lombok.Data;

@Data
public class Call {
    long number;
    long startCallTime;
    long endCallTime;
    CallType callType;

public Call getCall(Abonent abonent){
    Call call = new Call();

    return call;
}
}
