package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;
import java.util.Set;

public class UserOutputDto {
    public String role;
    public String firstName;
    public String lastName;
    public String street;
    public String houseNumber;
    public String postalCode;
    public String city;
    public String phoneNumber;
    public boolean enabled;
    public String username;
    public String country;
    public PlannerDto planner;
    public CustomerDto customer;
    public DriverDto driver;
    public Set<Authority> authorities;

    public static UserOutputDto fromUser(User u){
        UserOutputDto dto = new UserOutputDto();
        dto.role = u.getRole();
        dto.firstName = u.getFirstName();
        dto.lastName = u.getLastName();
        dto.street = u.getStreet();
        dto.houseNumber = u.getHouseNumber();
        dto.postalCode = u.getPostalCode();
        dto.city = u.getCity();
        dto.phoneNumber = u.getPhoneNumber();
        dto.enabled = u.isEnabled();
        dto.username = u.getUsername();
        dto.country = u.getCountry();
        dto.authorities = u.getAuthorities();
        if(u.getPlanner() != null) {
            dto.planner = PlannerDto.fromPlanner(u.getPlanner());
        }
        if(u.getCustomer() != null) {
            dto.customer = CustomerDto.fromCustomer(u.getCustomer());
        }
        if(u.getDriver() != null) {
            dto.driver = DriverDto.fromDriver(u.getDriver());
        }
        return dto;
    }
}




