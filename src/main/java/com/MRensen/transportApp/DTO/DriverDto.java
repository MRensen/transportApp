package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;

public class DriverDto {
    public String role = "driver";
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
    public String postalcode;
    public String phoneNumber;
    public String regularTruck;
    public String password;
    public String country;
    public boolean enabled;
//    public String image;

    public static DriverDto fromDriver(Driver d){
        DriverDto dto = new DriverDto();
        dto.id = d.getId();
        dto.country = d.getUser().getCountry();
        dto.username = d.getUser().getUsername();
        dto.postalcode = d.getUser().getPostalCode();
        dto.route = d.getRoutes();
        dto.firstName = d.getUser().getFirstName();
        dto.lastName = d.getUser().getLastName();
        dto.street = d.getUser().getStreet();
        dto.houseNumber =d.getUser().getHouseNumber();
        dto.city = d.getUser().getCity();
        dto.employeeNumber = d.getEmployeeNumber();
        dto.driverLicenseNumber = d.getDriverLicenseNumber();
        dto.phoneNumber = d.getUser().getPhoneNumber();
        dto.regularTruck = d.getRegularTruck();
        dto.password = d.getUser().getPassword();
        dto.enabled = d.getUser().isEnabled();
//        if(d.getUser().getImage() != null) {
//            dto.image = new String(d.getUser().getImage());
//        }
        return dto;
    }

    public Driver toDriver(){
        Driver d = new Driver();
        d.setUser(new User());
        d.getUser().setUsername(username);
        d.getUser().setCountry(country);
        d.getUser().setRole(role);
        d.getUser().setPostalCode(postalcode);
        d.setRoutes(route);
        d.getUser().setFirstName(firstName);
        d.getUser().setLastName(lastName);
        d.getUser().setStreet(street);
        d.getUser().setHouseNumber(houseNumber);
        d.getUser().setCity(city);
        d.setEmployeeNumber(employeeNumber);
        d.setDriverLicenseNumber(driverLicenseNumber);
        d.getUser().setPhoneNumber(phoneNumber);
        d.setRegularTruck(regularTruck);
        if(password != null) {
            d.getUser().setPassword(password);
        }
        d.getUser().setEnabled(enabled);
        return d;
    }
}