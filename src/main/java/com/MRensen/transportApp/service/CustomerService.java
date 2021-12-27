package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        Optional<Customer> cumstomerOption = customerRepository.findById(id);
        if(cumstomerOption.isPresent()) {
            return cumstomerOption.get();
        } else {
            throw new RecordNotFoundException("Customer Id was not found");
        }}

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){customerRepository.deleteById(id);}

    public void updateCustomer(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RecordNotFoundException("Customer not found");
        }
        Customer old = customerRepository.findById(id).orElse(null);
        old.setId(customer.getId());
        old.setAdress(customer.getAdress());
        old.setName(customer.getName());
    }
}