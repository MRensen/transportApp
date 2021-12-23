package com.MRensen.transportApp.utils;

public class BlockPallet extends Pallet{
    int width = 120;
    int length = 100;

    public BlockPallet(String load, int height) {
        super(load, height);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
