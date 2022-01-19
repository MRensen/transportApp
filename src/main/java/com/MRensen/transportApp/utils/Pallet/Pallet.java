package com.MRensen.transportApp.utils.Pallet;

import javax.persistence.*;

@Inheritance
@Entity
@Table(name="pallets")
public abstract class Pallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String load;
    int height;
    int weight;
    int width;
    int length;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
