package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders(){
        var orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        var order = orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("")
    public ResponseEntity<Object> postOrder(@RequestBody Order order){
        Order newOrder = orderService.addOrder(order);
        Long id = newOrder.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/type")
    public ResponseEntity<String> gettype(@PathVariable long id){
        String type = orderService.getType(id);
        return ResponseEntity.ok().body(type);
    }
}