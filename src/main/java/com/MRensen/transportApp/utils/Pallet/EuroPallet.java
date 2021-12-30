package com.MRensen.transportApp.utils.Pallet;

import com.MRensen.transportApp.utils.Pallet.Pallet;

public class EuroPallet extends Pallet {
    int width = 120;
    int length = 80;

    public EuroPallet(String load, int height) {
        super(load, height, "euro");
    }

    public EuroPallet() {
        super("euro");
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
