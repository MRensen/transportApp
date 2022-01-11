package com.MRensen.transportApp.DTO;


import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
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
        dto.username = c.getUsername();
        dto.myOrders = c.getMyOrders();
        dto.name = c.getName();
        dto.street = c.getStreet();
        dto.houseNumber = c.getHouseNumber();
        dto.postalCode = c.getPostalCode();
        dto.city = c.getCity();
        dto.phoneNumber = c.getPhoneNumber();
        dto.password = c.getPassword();
        dto.enabled = c.isEnabled();
        return dto;
    }

    public Customer toCustomer(){
        Customer c = new Customer();
        c.setId(id);
        c.setUsername(username);
        c.setMyOrders(myOrders);
        c.setName(name);
        c.setStreet(street);
        c.setHouseNumber(houseNumber);
        c.setPostalCode(postalCode);
        c.setCity(city);
        c.setPhoneNumber(phoneNumber);
        c.setPassword(password);
        c.setEnabled(enabled);
        return c;
    }
}
