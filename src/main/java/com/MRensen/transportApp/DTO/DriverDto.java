package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;

public class DriverDto {
    public Long id;
    public String username;
    @JsonIncludeProperties("id")
    public List<Route> route;
    public String firstName;
    public String lastName;
    public String street;
    public String houseNumber;
    public String city;
    public int employeeNumber;
    public String driverLicenseNumber;
    public String phoneNumber;
    public String regularTruck;
    public String password;
    public boolean enabled;

    public static DriverDto fromDriver(Driver d){
        DriverDto dto = new DriverDto();
        dto.id = d.getId();
        dto.username = d.getUsername();
        dto.route = d.getRoutes();
        dto.firstName = d.getFirstName();
        dto.lastName = d.getLastName();
        dto.street = d.getStreet();
        dto.houseNumber =d.getHouseNumber();
        dto.city = d.getCity();
        dto.employeeNumber = d.getEmployeeNumber();
        dto.driverLicenseNumber = d.getDriverLicenseNumber();
        dto.phoneNumber = d.getPhoneNumber();
        dto.regularTruck = d.getRegularTruck();
        dto.password = d.getPassword();
        dto.enabled = d.isEnabled();
        return dto;
    }

    public Driver toDriver(){
        Driver d = new Driver();
        d.setId(id);
        d.setUsername(username);
        d.setRoutes(route);
        d.setFirstName(firstName);
        d.setLastName(lastName);
        d.setStreet(street);
        d.setHouseNumber(houseNumber);
        d.setCity(city);
        d.setEmployeeNumber(employeeNumber);
        d.setDriverLicenseNumber(driverLicenseNumber);
        d.setPhoneNumber(phoneNumber);
        d.setRegularTruck(regularTruck);
        d.setPassword(password);
        d.setEnabled(enabled);
        return d;
    }
}