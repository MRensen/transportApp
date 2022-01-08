package com.MRensen.transportApp.utils;

import com.MRensen.transportApp.service.OrderService;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    ACCEPTED("accepted"),
    IN_TRANSPORT("in transport"),
    DELIVERED("delivered"),
    DECLINED("declined"),
    PROCESSING("processing");

    private String value;
    private OrderStatus(String value){
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
