package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="planners")
public class Planner implements UserInterface {
    @GeneratedValue
    @Id
    long id;

    @OneToOne
    User user;


    @JsonIgnore
    @OneToMany(mappedBy = "planner")
    List<Route> routes = new ArrayList<>();



    //CONSTRUCTORS

    public Planner() {
    }

    public Planner(String country, long id, String role, String firstName, String lastName, String street, String houseNumber, String postalCode, String city, String phoneNumber, List<Route> routes, String password, boolean enabled, String username, Set<Authority> authorities) {
        this.id = id;
        this.user =  new User(country,  role,  postalCode,  username,   firstName,  lastName,  street,  houseNumber,  city,  phoneNumber,  password,  authorities);
        this.routes = routes;
    }

    //GETTERS AND SETTERS




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route){ this.routes.add(route);}

    public void deleteRoute(Route route){this.routes.remove(route);}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
