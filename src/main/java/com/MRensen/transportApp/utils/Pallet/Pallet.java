package com.MRensen.transportApp.utils.Pallet;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Pallet {
    String load;
    int height;
    int weight;
    PalletType type = PalletType.NONE;

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

    public Pallet(String load, int height, PalletType type) {
        this.load = load;
        this.height = height;
        this.type = type;
    }

    public Pallet(PalletType type) {
        this.type = type;
    }

    //this is useless, but I get an error is I don't put this in
    public Pallet(){}

    public PalletType getType() {
        return type;
    }

    public void setType(PalletType type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
