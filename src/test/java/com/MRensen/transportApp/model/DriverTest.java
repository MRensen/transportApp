package com.MRensen.transportApp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverTest {

    private Driver driver;
    private Route route;

    @BeforeEach
    void setup(){
        driver = new Driver();
        route = new Route();
    }

    @Test
    void emptyRoutesReturnEmptyArrayList(){
        var actual = driver.getRoutes();
        var expected = new ArrayList<>();
        assertEquals(expected,actual);
    }

    @Test
    void getRouteReturnsValueWhenAddRoute(){
        var testList = new ArrayList<>();
        testList.add(route);
        driver.addRoute(route);
        var actual = driver.getRoutes();
        var expected = testList;
        assertEquals(expected, actual);
    }
}