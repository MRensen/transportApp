package com.MRensen.transportApp.utils;

public abstract class Pallet {
    String load;
    int height;

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

    public Pallet(String load, int height) {
        this.load = load;
        this.height = height;
    }
}
