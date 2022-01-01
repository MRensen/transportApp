package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;
import java.util.Set;

public class PlannerDto {
    public Long id;
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
    public Set<Authority> authorities;

    public static PlannerDto fromPlanner(Planner p){
        PlannerDto dto = new PlannerDto();
        dto.id = p.getId();
        dto.firstName = p.getFirstName();
        dto.lastName = p.getLastName();
        dto.street = p.getStreet();
        dto.houseNumber = p.getHouseNumber();
        dto.postalCode = p.getPostalCode();
        dto.city = p.getCity();
        dto.phoneNumber = p.getPhoneNumber();
        dto.routes = p.getRoutes();
        dto.password = p.getPassword();
        dto.enabled = p.isEnabled();
        dto.username = p.getUsername();
        dto.authorities = p.getAuthorities();
        return dto;
    }

    public Planner toPlanner(){
        Planner p = new Planner();
        p.setId(id);
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setStreet(street);
        p.setHouseNumber(houseNumber);
        p.setPostalCode(postalCode);
        p.setCity(city);
        p.setPhoneNumber(phoneNumber);
        p.setRoutes(routes);
        p.setPassword(password);
        p.setEnabled(enabled);
        p.setUsername(username);
        p.setAuthorities(authorities);
        return p;
    }
}