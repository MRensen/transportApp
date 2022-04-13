package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.utils.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void findAllByOrderStatusTest(){
        Order order = new Order();
        order.setDescription("testing");
        order.setOrderStatus(OrderStatus.PROCESSING);
        Order order1 = new Order();
        order1.setOrderStatus(OrderStatus.IN_TRANSPORT);
        order1.setDescription("not testing");
        Order order2 = order;
        entityManager.persist(order);
        entityManager.persist(order1);
        entityManager.flush();

        List<Order> actual = orderRepository.findAllByOrderStatus(OrderStatus.PROCESSING);

        assertEquals("testing", actual.get(0).getDescription());
    }

    @Test
    public void findAllByRoute(){
        Route route = new Route();
        route.setTruck("test");
        Order order = new Order();
        order.setRoute(route);
        order.setDescription("testing");
        entityManager.persist(route);
        entityManager.persist(order);
        entityManager.flush();

        List<Order> actual = orderRepository.findAllByRoute(route);

        assertEquals("testing", actual.get(0).getDescription());
    }
}