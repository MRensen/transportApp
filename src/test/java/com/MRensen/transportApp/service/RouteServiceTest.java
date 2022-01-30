package com.MRensen.transportApp.service;


import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.model.*;
import com.MRensen.transportApp.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest()
@ContextConfiguration(classes={TransportAppApplication.class})
@EnableConfigurationProperties
public class RouteServiceTest {

    @Autowired
    RouteService routeService;

    @MockBean
    RouteRepository routeRepository;

    @Mock
    Route route;

    @MockBean
    DriverService driverService;

    @MockBean
    PlannerService plannerService;

    @Mock
    OrderService orderService;



    List<Order> orderList;


    @BeforeEach
    void setup(){
        Driver driver = new Driver();
        driver.setId(10L);
        Planner planner = new Planner();
        planner.setId(20L);
        orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        route = new Route("97-bph-8", driver, planner, orderList);
    }

    @Test
    void getAllRoutesReturnsArraylist(){
        var routeList = new ArrayList<Route>();
        routeList.add(route);
        Mockito
                .when(routeRepository.findAll())
                .thenReturn(routeList);

        var actual = routeService.getAllRoutes();

        assertEquals(routeList, actual);
    }

    @Test
    void getRouteReturnsRoute(){
        Mockito
                .when(routeRepository.findById(anyLong()))
                .thenReturn(Optional.of(route));
        var actual = routeService.getRoute(1L);
        assertEquals("97-bph-8", actual.getTruck());
    }

    @Test
    void addRouteReturnsRoute(){

        route.setOrders(new ArrayList<Order>());

        Mockito
                .when(routeRepository.save(route))
                .thenReturn(route);

        Mockito
                .doNothing()
                .when(driverService)
                .addDriverRoute(anyLong(), anyLong());

        Mockito
                .doNothing()
                .when(plannerService)
                .addPlannerRoute(anyLong(), anyLong());


        var actual = routeService.addRoute(route);
        assertEquals("97-bph-8", actual.getTruck());
    }






}