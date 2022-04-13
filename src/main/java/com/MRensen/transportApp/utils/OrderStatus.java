package com.MRensen.transportApp.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    ACCEPTED("accepted"),
    IN_TRANSPORT("in transport"),
    DELIVERED("delivered"),
    NOT_DELIVERED("not delivered"),
    DECLINED("declined"),
    PROCESSING("processing");

    private String value;
     OrderStatus(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
