package com.MRensen.transportApp.utils;

public class EuroPallet extends Pallet{
    int width = 120;
    int length = 80;

    public EuroPallet(String load, int height) {
        super(load, height);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
