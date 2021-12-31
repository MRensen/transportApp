package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="routes")
public class Route {
    @GeneratedValue
    @Id
    long id;

    //security
    String password;
    boolean enabled = true;
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    String truck; //license plate
    @OneToOne(cascade = CascadeType.ALL)
    Driver driver;
    @ManyToOne
    Planner planner;
    @OneToMany(mappedBy = "route")
    List<Order> orders;

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void addAuthority(String authority) {
        this.authorities.add(new Authority(this.id, authority));
    }

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
