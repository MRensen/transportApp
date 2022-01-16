package com.MRensen.transportApp.DTO;


import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;
import java.util.Set;

public class CustomerDto {
    public Long id;
    public String username;
    public String role = "customer";
    @JsonIncludeProperties("id")
    public List<Order> myOrders;
    public String name;
    public String street;
    public String houseNumber;
    public String postalCode;
    public String city;
    public String phoneNumber;
    public String password;
    public boolean enabled;

    public static CustomerDto fromCustomer(Customer c){
        CustomerDto dto = new CustomerDto();
        dto.id = c.getId();
        dto.username = c.getUser().getUsername();
        dto.myOrders = c.getMyOrders();
        dto.name = c.getName();
        dto.street = c.getUser().getStreet();
        dto.houseNumber = c.getUser().getHouseNumber();
        dto.postalCode = c.getUser().getPostalCode();
        dto.city = c.getUser().getCity();
        dto.phoneNumber = c.getUser().getPhoneNumber();
        dto.password = c.getUser().getPassword();
        dto.enabled = c.getUser().isEnabled();
        return dto;
    }

    public Customer toCustomer(){
        Customer c = new Customer();
        c.setUser(new User());
        c.getUser().setUsername(username);
        c.setMyOrders(myOrders);
        c.setName(name);
        c.getUser().setStreet(street);
        c.getUser().setHouseNumber(houseNumber);
        c.getUser().setPostalCode(postalCode);
        c.getUser().setCity(city);
        c.getUser().setPhoneNumber(phoneNumber);
        if(password != null) {
            c.getUser().setPassword(password);
        }
        c.getUser().setEnabled(enabled);
        return c;
    }
}
