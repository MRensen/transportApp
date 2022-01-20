package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.repository.CustomerRepository;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserServiceInt<Customer> {
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer getOne(Long id){
        Optional<Customer> cumstomerOption = customerRepository.findById(id);
        if(cumstomerOption.isPresent()) {
            return cumstomerOption.get();
        } else {
            throw new RecordNotFoundException("Customer Id was not found");
        }}

    public Customer addOne(Customer customer) {
        userRepository.save(customer.getUser());
        return customerRepository.save(customer);
    }

    public void deleteOne(Long id){customerRepository.deleteById(id);}

    public Customer patchOne(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RecordNotFoundException("Customer not found");
        }
        Customer old = customerRepository.findById(id).orElse(null);
        if(customer.getUser().getStreet() != null){
            old.getUser().setStreet(customer.getUser().getStreet());
        }
        if(customer.getUser().getCountry()!= null){
            old.getUser().setCountry(customer.getUser().getCountry());
        }
        if(customer.getName() != null) {
            old.setName(customer.getName());
        }
        if(customer.getUser().getHouseNumber() != null) {
            old.getUser().setHouseNumber(customer.getUser().getHouseNumber());
        }
        if(customer.getUser().getPostalCode() != null) {
            old.getUser().setPostalCode(customer.getUser().getPostalCode());
        }
        if(customer.getUser().getCity() != null) {
            old.getUser().setCity(customer.getUser().getCity());
        }
        if(customer.getUser().getPhoneNumber() != null) {
            old.getUser().setPhoneNumber(customer.getUser().getPhoneNumber());
        }
        customerRepository.save(old);
        return old;
    }


    public Customer updateOne(Long id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RecordNotFoundException("Customer not found");
        }
        Customer old = customerRepository.findById(id).orElse(null);
        old.getUser().setStreet(customer.getUser().getStreet());
        old.setMyOrders(customer.getMyOrders());
        old.setName(customer.getName());
        old.getUser().setCountry(customer.getUser().getCountry());
        old.getUser().setHouseNumber(customer.getUser().getHouseNumber());
        old.getUser().setPostalCode(customer.getUser().getPostalCode());
        old.getUser().setCity(customer.getUser().getCity());
        old.getUser().setPhoneNumber(customer.getUser().getPhoneNumber());
        customerRepository.save(old);
        return old;
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