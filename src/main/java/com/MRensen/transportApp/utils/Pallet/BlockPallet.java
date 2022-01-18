package com.MRensen.transportApp.utils.Pallet;

import javax.persistence.Entity;

@Entity
public class BlockPallet extends Pallet {
    int width = 120;
    int length = 100;

    public BlockPallet(String load, int height) {
        super(load, height, PalletType.BLOCK);
    }

    public BlockPallet() {
        super(PalletType.BLOCK);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
