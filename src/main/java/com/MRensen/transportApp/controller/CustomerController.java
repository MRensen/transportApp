package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.CustomerDto;
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
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        var c = customerService.getAllCustomers().stream().map(CustomerDto::fromCustomer).toList();
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id){
        CustomerDto customer = CustomerDto.fromCustomer(customerService.getCustomer(id));
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long id){
        var orders = customerService.getOrders(id);
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("")
    public ResponseEntity<Object> postCustomer(@RequestBody CustomerDto customer){
        Customer newCustomer = customerService.addCustomer(customer.toCustomer());
        Long id = newCustomer.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putCustomer(@PathVariable Long id, @RequestBody CustomerDto customer){
        customerService.updateCustomer(id, customer.toCustomer());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customer){
        customerService.patchCustomer(id, customer.toCustomer());
        return ResponseEntity.noContent().build();
    }
}