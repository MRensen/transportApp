package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}