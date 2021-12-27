package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Planner {
    @GeneratedValue
    @Id
    long id;

    String name;

    @OneToMany(mappedBy = "planner")
    List<Route> routes = new ArrayList<>();


}
