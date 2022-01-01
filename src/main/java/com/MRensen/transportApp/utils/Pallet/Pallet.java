package com.MRensen.transportApp.utils.Pallet;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Pallet {
    String load;
    int height;
    int weight;
    String type = "none";

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

    //this is useless, but I get an error is I don't put this in
    public Pallet(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
