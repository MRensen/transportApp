package com.MRensen.transportApp.Repository;

import com.MRensen.transportApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}