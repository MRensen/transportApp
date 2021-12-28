package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrder(Long id){
        Optional<Order> orderOption = orderRepository.findById(id);
        if(orderOption.isPresent()) {
            return orderOption.get();
        } else {
            throw new RecordNotFoundException("Order Id was not found");
        }}

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){orderRepository.deleteById(id);}

    public void updateOrder(Long id, Order order){
        if(!orderRepository.existsById(id)){
            throw new RecordNotFoundException("Order not found");
        }
        Order old = orderRepository.findById(id).orElse(null);
//        old.setId(customer.getId());
//        old.setAdress(customer.getAdress());
//        old.setName(customer.getName());
    }
}