package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements AccountInterface {
    @GeneratedValue
    @Id
    long id;

    String name;

    @OneToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    List<Order> myOrders;

    //GETTERS AND SETTERS

    public Customer(String country, long id, String username, String firstName, String lastName, String street, List<Order> myOrders, String passWord, Set<Authority> authorities, String houseNumber, String postalCode, String city, String phoneNumber) {
        this.id = id;
        this.myOrders = myOrders;
        this.user = new User(country, "customer", postalCode, username, firstName, lastName, street, houseNumber, city, phoneNumber, passWord, authorities);
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
