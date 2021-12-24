package com.MRensen.transportApp.Model;

import javax.persistence.*;

@Entity
public class Driver {
    @GeneratedValue
    @Id
    long id;

    @ManyToOne
    Planner planner;

    String firstName;
    String lastName;
    String adress;
}
