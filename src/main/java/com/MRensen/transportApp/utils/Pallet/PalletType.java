package com.MRensen.transportApp.utils.Pallet;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PalletType {
    EURO("euro"),
    BLOCK("block"),
    OTHER("other"),
    NONE( "none");

    private PalletType(String value){
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
