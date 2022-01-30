package com.MRensen.transportApp.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {

    private Planner planner;
    private Route route;

    @BeforeEach
    void setup(){
        planner = new Planner();
        route = new Route();
    }

    @Test
    void emptyRoutesReturnEmptyArrayList(){
        var actual = planner.getRoutes();
        var expected = new ArrayList<>();
        assertEquals(expected,actual);
    }

    @Test
    void getRouteReturnsValueWhenAddRoute(){
        var testList = new ArrayList<>();
        testList.add(route);
        planner.addRoute(route);
        var actual = planner.getRoutes();
        var expected = testList;
        assertEquals(expected, actual);
    }
}