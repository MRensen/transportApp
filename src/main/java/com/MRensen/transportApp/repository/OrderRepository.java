package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.utils.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}