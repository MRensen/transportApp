package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="routes")
public class Route {
    @GeneratedValue
    @Id
    long id;

    String truck; //license plate

    @ManyToOne
    Driver driver;

    @ManyToOne
    Planner planner;

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    List<Order> orders;

    //CONSTRUCTORS

    public Route() {
        this.orders = new ArrayList<>();
    }

    public Route(String truck, Driver driver, Planner planner, List<Order> orders) {
        this.truck = truck;
        this.driver = driver;
        this.planner = planner;
        this.orders = orders;
    }

    // GETTERS AND SETTERS


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){this.orders.add(order);}

    public void removeOrder(Order order){this.orders.remove(order);}
}
