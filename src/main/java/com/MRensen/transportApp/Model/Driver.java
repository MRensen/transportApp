package com.MRensen.transportApp.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Driver {
    @GeneratedValue
    @Id
    long id;

    @OneToMany
    Planner planner;

    String firstName;
    String lastName;
    String adress;
}
