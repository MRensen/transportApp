package com.MRensen.transportApp.model;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {
    private Route route;
    private Order order;

    @BeforeEach
    void setup(){
        route = new Route();
        order = new Order();
    }

    @Test
    void getEmptyOrderReturnsEmptyArrayList(){
        var expected = new ArrayList<>();
        var actual = route.getOrders();
        assertEquals(expected, actual);
    }

    @Test
    void getOrdersReturnsArrayList(){
        Order order2= new Order();
        var testList = new ArrayList<Order>();
        testList.add(order);
        testList.add(order2);
        route.setOrders(testList);
        var expected = testList;
        var actual = route.getOrders();
    }
}