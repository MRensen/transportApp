package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @GeneratedValue
    @Id
    long id;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    List<Order> myOrders;

    String name;
    String street;
    String houseNumber;
    String postalCode;
    String city;
    String phoneNumber;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(long id, List<Order> myOrders, String name, String street, String houseNumber, String postalCode, String city, String phoneNumber) {
        this.id = id;
        this.myOrders = myOrders;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }
}
