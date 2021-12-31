package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
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

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers(){
        var c = customerService.getAllCustomers();
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long id){
        var orders = customerService.getOrders(id);
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("")
    public ResponseEntity<Object> postCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);
        Long id = newCustomer.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public void putCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
    }

    @PatchMapping("/{id}")
    public void patchCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customerService.patchCustomer(id, customer);
    }
}