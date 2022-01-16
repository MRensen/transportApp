package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;
import java.util.Set;

public class PlannerDto {
    public Long id;
    public String role = "planner";
    public String firstName;
    public String lastName;
    public String street;
    public String houseNumber;
    public String postalCode;
    public String city;
    public String phoneNumber;
    @JsonIncludeProperties("id")
    public List<Route> routes;
    public String password;
    public boolean enabled;
    public String username;

    public static PlannerDto fromPlanner(Planner p){
        PlannerDto dto = new PlannerDto();
        dto.id = p.getId();
        dto.firstName = p.getUser().getFirstName();
        dto.lastName = p.getUser().getLastName();
        dto.street = p.getUser().getStreet();
        dto.houseNumber = p.getUser().getHouseNumber();
        dto.postalCode = p.getUser().getPostalCode();
        dto.city = p.getUser().getCity();
        dto.phoneNumber = p.getUser().getPhoneNumber();
        dto.routes = p.getRoutes();
        dto.password = p.getUser().getPassword();
        dto.enabled = p.getUser().isEnabled();
        dto.username = p.getUser().getUsername();
        return dto;
    }

    public Planner toPlanner(){
        Planner p = new Planner();
        p.setUser(new User());
        p.getUser().setFirstName(firstName);
        p.getUser().setLastName(lastName);
        p.getUser().setStreet(street);
        p.getUser().setHouseNumber(houseNumber);
        p.getUser().setPostalCode(postalCode);
        p.getUser().setCity(city);
        p.getUser().setPhoneNumber(phoneNumber);
        p.setRoutes(routes);
        p.getUser().setPassword(password);
        p.getUser().setEnabled(enabled);
        p.getUser().setUsername(username);
        return p;
    }
}