package com.MRensen.transportApp.DTO;

import java.io.Serializable;
import java.util.Objects;

public class CustomerDto implements Serializable {
    private final long id;
    private final String name;
    private final String adress;

    public CustomerDto(long id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "adress = " + adress + ")";
    }
}
