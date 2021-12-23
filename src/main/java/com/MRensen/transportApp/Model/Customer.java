package com.MRensen.transportApp.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
    @GeneratedValue
    @Id
    long id;
    String name;
    String adress;
}
