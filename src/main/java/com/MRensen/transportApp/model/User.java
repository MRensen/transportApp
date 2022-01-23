package com.MRensen.transportApp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
//
    @Transient
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Column(nullable = false, unique = true)
    @Id
    String username;
    String role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    Planner planner;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    Driver driver;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    Customer customer;

    @Lob
    byte[] image;
    
    String firstName;
    String lastName;
    String street;
    String houseNumber;
    String city;
    String postalCode;
    String phoneNumber;
    String password;
    String country;
    boolean enabled = true;
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    // constructor


    public User() {
    }

    public User(String country, String role, String postalCode, String username, String firstName, String lastName, String street, String houseNumber, String city, String phoneNumber, String password, Set<Authority> authorities) {
        this.password = passwordEncoder.encode(password);
        this.country = country;
        this.username = username;
        this.postalCode = postalCode;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.phoneNumber = phoneNumber;
        if(authorities.isEmpty()){
            this.authorities.add(new Authority(username, role));
        }else {
            this.authorities = authorities;
        }
    }

    //getter setters


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        this.authorities.add(new Authority(this.username, role));
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
