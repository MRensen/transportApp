package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="routes")
public class Route {
    @GeneratedValue
    @Id
    long id;

    String truck; //license plate

    @OneToOne
    Driver driver;

    @ManyToOne
    Planner planner;

    @OneToMany(mappedBy = "route")
    List<Order> orders;
}
