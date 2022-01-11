package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver {
    @GeneratedValue
    long id;
    String role;

    @Column(nullable = false,unique = true)
    @Id
    String username;

    @OneToMany(
            mappedBy = "driver",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    List<Route> routes;

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

    //security
    String password;
    boolean enabled = true;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();


    //CONSTRUCTORS

    public Driver() {
    }

    public Driver(long id, List<Route> route, String firstName, String lastName, String street, String houseNumber, String city, int employeeNumber, String driverLicenseNumber, String phoneNumber, String regularTruck) {
        this.id = id;
        this.routes = route;
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

    //GETTERS AND SETTERS

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void addAuthority(String authority) {
        this.authorities.add(new Authority(this.username, authority));
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

    public List<Route> getRoutes() {
        return routes;
    }
    public void addRoutes(Route route){
        routes.add(route);
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
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
