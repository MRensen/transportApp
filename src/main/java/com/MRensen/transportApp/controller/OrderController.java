package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.OrderDto;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.service.OrderService;
import com.MRensen.transportApp.utils.Pallet.PalletType;
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
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id){
        var order = OrderDto.fromOrder(orderService.getOrder(id));
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
    public ResponseEntity<Object> gettype(@PathVariable long id){
        PalletType type = orderService.getType(id);
        return ResponseEntity.ok().body(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putOrder(@PathVariable Long id, @RequestBody OrderDto order){
        orderService.updateOrder(id, order.toOrder());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchOrder(@PathVariable Long id, @RequestBody OrderDto order){
        orderService.patchOrder(id, order.toOrder());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}