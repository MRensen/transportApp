package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.IdDto;
import com.MRensen.transportApp.DTO.OrderDto;
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
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        var orders = orderService.getAllOrders().stream().map(OrderDto::fromOrder).toList();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable IdDto id){
        var order = OrderDto.fromOrder(orderService.getOrder(id.id));
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("")
    public ResponseEntity<Object> postOrder(@RequestBody OrderDto order){
        Order newOrder = orderService.addOrder(order.toOrder());
        Long id = newOrder.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/type")
    public ResponseEntity<String> gettype(@PathVariable IdDto id){
        String type = orderService.getType(id.id);
        return ResponseEntity.ok().body(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putOrder(@PathVariable IdDto id, @RequestBody OrderDto order){
        orderService.updateOrder(id.id, order.toOrder());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchOrder(@PathVariable IdDto id, @RequestBody OrderDto order){
        orderService.patchOrder(id.id, order.toOrder());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable IdDto id){
        orderService.deleteOrder(id.id);
        return ResponseEntity.noContent().build();
    }
}