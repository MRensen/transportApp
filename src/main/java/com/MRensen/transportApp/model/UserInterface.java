package com.MRensen.transportApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

public interface UserInterface {


    public long getId();

    public void setId(long Id);

    public User getUser();

    public void setUser(User user);


//    public String getUsername();
//
//    public void setUsername(String username);
//
//    public String getPassword();
//
//    public void setPassword(String password);
//
//    public boolean isEnabled();
//
//    public void setEnabled(boolean isEnabled);
//
//    public Set<Authority> getAuthorities();
//
//    public void setAuthorities(Set<Authority> authorities);
//
//    public void addAuthority(Authority authority);
//
//    public void addAuthority(String Authority);
//
//    public String getPhoneNumber();
//
//    public void setPhoneNumber(String phoneNumber);
//
//
//
//    public String getStreet();
//
//    public void setStreet(String street);
//
//    public String getHouseNumber();
//
//    public void setHouseNumber(String number);
//
//    public String getCity();
//
//    public void setCity(String city);

}
