package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
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

    public void patchCustomer(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RecordNotFoundException("Customer not found");
        }
        Customer old = customerRepository.findById(id).orElse(null);
        if(customer.getStreet() != null){
            old.setStreet(customer.getStreet());
        }
        if(customer.getName() != null) {
            old.setName(customer.getName());
        }
        if(customer.getHouseNumber() != null) {
            old.setHouseNumber(customer.getHouseNumber());
        }
        if(customer.getPostalCode() != null) {
            old.setPostalCode(customer.getPostalCode());
        }
        if(customer.getCity() != null) {
            old.setCity(customer.getCity());
        }
        if(customer.getPhoneNumber() != null) {
            old.setPhoneNumber(customer.getPhoneNumber());
        }
        customerRepository.save(old);
    }

    public void updateCustomer(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RecordNotFoundException("Customer not found");
        }
        Customer old = customerRepository.findById(id).orElse(null);
        old.setStreet(customer.getStreet());
        old.setName(customer.getName());
        old.setHouseNumber(customer.getHouseNumber());
        old.setPostalCode(customer.getPostalCode());
        old.setCity(customer.getCity());
        old.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(old);
    }

   public List<Order> getOrders(Long id) {
       Optional<Customer> customerOption = customerRepository.findById(id);
       if (customerOption.isPresent()) {
           Customer customer = customerOption.get();
           return customer.getMyOrders();
       } else {
           throw new RecordNotFoundException("Customer Id was not found");
       }
   }

}