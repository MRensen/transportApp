package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="drivers")
public class Driver {
    @GeneratedValue
    @Id
    long id;

    @OneToOne
    @JsonIgnoreProperties("driver")
    @JoinColumn(name="route_id")
    Route route;

    //personal details
    String firstName;
    String lastName;
    String street;
    String houseNumber;
    String city;

    int employeeNumber;
    String driverLicenseNumber;
    String phoneNumber;
    String regularTruck; //license plate

    public Driver() {
    }

    public Driver(long id, Route route, String firstName, String lastName, String street, String houseNumber, String city, int employeeNumber, String driverLicenseNumber, String phoneNumber, String regularTruck) {
        this.id = id;
        this.route = route;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.employeeNumber = employeeNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.phoneNumber = phoneNumber;
        this.regularTruck = regularTruck;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegularTruck() {
        return regularTruck;
    }

    public void setRegularTruck(String regularTruck) {
        this.regularTruck = regularTruck;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
