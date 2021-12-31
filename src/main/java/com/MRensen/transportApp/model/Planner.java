package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="planners")
public class Planner {
    @GeneratedValue
    @Id
    long id;

    //security
    String password;
    boolean enabled = true;
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    String name;
    @OneToMany(mappedBy = "planner")
    List<Route> routes = new ArrayList<>();


    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void addAuthority(String authority) {
        this.authorities.add(new Authority(this.id, authority));
    }
}
