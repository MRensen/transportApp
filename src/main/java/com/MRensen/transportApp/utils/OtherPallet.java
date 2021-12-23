package com.MRensen.transportApp.utils;

public class OtherPallet extends Pallet{
    int width;
    int length;

    public OtherPallet(String load, int height, int width, int length) {
        super(load, height);
        this.width = width;
        this.length = length;
    }

    public OtherPallet(String load, int height) {
        super(load, height);
        this.width = 0;
        this.length = 0;
    }
}
