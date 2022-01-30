package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver implements UserInterface {
    @Id
    @GeneratedValue
    long id;

    @OneToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    User user;

    @OneToMany(
            mappedBy = "driver",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    List<Route> routes;

    //personal details

    @Column(unique = true)
    int employeeNumber;
    String driverLicenseNumber;
    String regularTruck; //license plate


    //CONSTRUCTORS

    public Driver() {
        this.routes = new ArrayList<>();
    }

    public Driver(String country, long id,String postal, String username, List<Route> route, String firstName, String lastName, Set<Authority> authorities, String passWord, String street, String houseNumber, String city, int employeeNumber, String driverLicenseNumber, String phoneNumber, String regularTruck) {
        this.id = id;
        this.routes = route;
        this.user = new User(country,"driver",postal, username,  firstName, lastName, street, houseNumber, city, phoneNumber, passWord, authorities);
        this.employeeNumber = employeeNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.regularTruck = regularTruck;
    }

    //GETTERS AND SETTERS


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getRegularTruck() {
        return regularTruck;
    }

    public void setRegularTruck(String regularTruck) {
        this.regularTruck = regularTruck;
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public void deleteRoute(Route route){
        this.routes.remove(route);
    }


}
