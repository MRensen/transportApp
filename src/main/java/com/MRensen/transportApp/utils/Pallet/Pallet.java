package com.MRensen.transportApp.utils.Pallet;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Pallet {
    String load;
    int height;
    String type;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public Pallet(String load, int height, String type) {
        this.load = load;
        this.height = height;
        this.type = type;
    }

    public Pallet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
