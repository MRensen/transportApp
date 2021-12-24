package com.MRensen.transportApp.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Route {
    @GeneratedValue
    @Id
    long id;

    @ManyToOne
    Planner planner;

    @OneToMany(mappedBy = "route")
    List<Order> orders;
}
