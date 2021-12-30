package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="routes")
public class Route {
    @GeneratedValue
    @Id
    long id;

    String truck; //license plate

    @OneToOne(cascade = CascadeType.ALL)
    Driver driver;

    @ManyToOne
    Planner planner;

    @OneToMany(mappedBy = "route")
    List<Order> orders;

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
}
