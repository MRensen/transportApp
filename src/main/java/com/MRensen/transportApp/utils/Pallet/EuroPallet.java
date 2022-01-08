package com.MRensen.transportApp.utils.Pallet;

import com.MRensen.transportApp.utils.Pallet.Pallet;

import javax.persistence.Entity;

@Entity
public class EuroPallet extends Pallet {
    int width = 120;
    int length = 80;

    public EuroPallet(String load, int height) {
        super(load, height, PalletType.EURO);
    }

    public EuroPallet() {
        super(PalletType.EURO);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
