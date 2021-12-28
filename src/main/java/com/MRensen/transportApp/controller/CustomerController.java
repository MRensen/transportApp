package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> getCustomers(){
        //return customerService.getAllCustomers();
        ArrayList<Customer> c = new ArrayList<>();
        c.add(new Customer(1L, "henk", "straat"));
        c.add(new Customer(2L, "piet", "laan"));
        return c;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
//        long id = intid;
        System.out.println(id);
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("")
    public ResponseEntity<Object> postCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);
        Long id = newCustomer.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}