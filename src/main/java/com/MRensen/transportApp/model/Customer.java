package com.MRensen.transportApp.model;

import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @GeneratedValue
    @Id
    long id;

    @OneToMany
    List<Order> myOrders;

    String name;
    String adress;

    public Customer(long id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
