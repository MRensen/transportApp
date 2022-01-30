package com.MRensen.transportApp.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    void emptyMyOrdersReturnsEmptyArrayList(){
        Customer c = new Customer();
        var expected = new ArrayList<>();
        var actual = c.getMyOrders();
        assertEquals(expected, actual);
    }

}