package com.MRensen.transportApp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "authorities")
@IdClass(com.MRensen.transportApp.model.AuthorityKey.class)
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private Long id;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
