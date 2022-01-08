package com.MRensen.transportApp.utils.Pallet;

import com.MRensen.transportApp.utils.Pallet.Pallet;

import javax.persistence.Entity;

@Entity
public class OtherPallet extends Pallet {
    int width;
    int length;


    public OtherPallet() {
        super(PalletType.OTHER);
    }

    public OtherPallet(String load, int height, int width, int length) {
        super(load, height, PalletType.OTHER);
        this.width = width;
        this.length = length;
    }

    public OtherPallet(String load, int height) {
        super(load, height, PalletType.OTHER);
        this.width = 0;
        this.length = 0;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
