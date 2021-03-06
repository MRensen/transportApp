package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.OrderDto;
import com.MRensen.transportApp.DTO.PalletDto;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.service.OrderService;
import com.MRensen.transportApp.utils.OrderStatus;
import com.MRensen.transportApp.utils.Pallet.Pallet;
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

    @PutMapping("/{id}/pallet")
    public ResponseEntity<Object> addPallet(@PathVariable Long id, @RequestBody PalletDto pallet){
        orderService.addPallet(id, pallet.toPallet());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/pallets")
    public ResponseEntity<Object> getPallets(@PathVariable Long id){
        List<Pallet> pallets = orderService.getPallets(id);
        List<PalletDto> palletDtos = pallets.stream().map(PalletDto::fromPallet).toList();
        return ResponseEntity.ok().body(palletDtos);
    }
    @GetMapping("/{id}/type")
    public ResponseEntity<Object> getType(@PathVariable long id){
        PalletType type = orderService.getType(id);
        return ResponseEntity.ok().body(type);
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Object> getByRoute(@PathVariable long id){
        var result = orderService.getOrdersByRoute(id).stream().map(OrderDto::fromOrder).toList();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/status/open")
    public ResponseEntity<Object> getOpenOrders(){
        var result = orderService.getOrdersByStatus(OrderStatus.PROCESSING).stream().map(OrderDto::fromOrder).toList();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/status/delivered")
    public ResponseEntity<Object> getDeliveredOrders(){
        var result = orderService.getOrdersByStatus(OrderStatus.DELIVERED).stream().map(OrderDto::fromOrder).toList();
        return ResponseEntity.ok().body(result);
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