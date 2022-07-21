package com.MRensen.transportApp.service;


import com.MRensen.transportApp.model.*;
import com.MRensen.transportApp.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @Mock
    RouteRepository routeRepository;

    Route route;

    @Mock
    DriverService driverService;

    @Mock
    PlannerService plannerService;

    @Mock
    OrderService orderService;


    @InjectMocks
    RouteService routeService = new RouteService(routeRepository, orderService, plannerService, driverService);

    List<Order> orderList;


    @BeforeEach
    void setup(){
        Driver driver = new Driver();
        driver.setId(10L);
        driver.setRoutes(new ArrayList<Route>());
        Planner planner = new Planner();
        planner.setId(20L);
        orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        route = new Route("97-bph-8", driver, planner, orderList);
        route.setId(1L);
    }

    @Test
    void getAllRoutesReturnsArraylist(){
        var routeList = new ArrayList<Route>();
        routeList.add(route);
        when(routeRepository.findAll())
                .thenReturn(routeList);

        var actual = routeService.getAllRoutes();

        assertEquals(routeList, actual);
    }

    @Test
    void getRouteReturnsRoute(){
        when(routeRepository.findById(anyLong()))
                .thenReturn(Optional.of(route));
        var actual = routeService.getRoute(1L);
        assertEquals("97-bph-8", actual.getTruck());
    }

    @Test
    void addRouteReturnsRoute(){

        route.setOrders(new ArrayList<Order>());

        when(routeRepository.save(route))
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

//    @Test
//    void deleteRouteTest(){
//        route.setOrders(new ArrayList<Order>());
//
//        Mockito
//                .when(routeRepository.findById(anyLong()))
//                .thenReturn(Optional.of(route));
//
//        Mockito
//                .when(driverService.patchOne(anyLong(), any(Driver.class)))
//                .thenReturn(route.getDriver());
//
//        Mockito
//                .when(plannerService.patchOne(anyLong(), any(Planner.class)))
//                .thenReturn(route.getPlanner());
//
//        Mockito
//                .doNothing()
//                .when(orderService).patchOrder(anyLong(), any(Order.class));
//
//        routeService.deleteRoute(route.getId());
//        assertFalse(routeRepository.existsById(route.getId()));
//
//
//    }

    @Test
    void updateRouteTest(){
        Mockito
                .when(routeRepository.findById(anyLong()))
                .thenReturn(Optional.of(route));

        Mockito
                .when(routeRepository.existsById(anyLong()))
                .thenReturn(true);

        Mockito
                .when(driverService.getOne(anyLong()))
                .thenReturn(route.getDriver());

        Mockito
                .when(plannerService.getOne(anyLong()))
                .thenReturn(route.getPlanner());

        Mockito
                .when(routeRepository.save(any(Route.class)))
                .thenReturn(route);
        Route expectedRoute = new Route("test", route.getDriver(), route.getPlanner(), route.getOrders());
        Route newRoute = routeService.updateRoute(route.getId(), expectedRoute);
        assertEquals("test", newRoute.getTruck());
    }

    @Test
    void patchRouteTest(){

        route.setOrders(null);

            Mockito
                    .when(routeRepository.existsById(anyLong()))
                    .thenReturn(true);

            Mockito
                    .when(routeRepository.findById(anyLong()))
                    .thenReturn(Optional.of(route));

            Mockito
                    .when(driverService.getOne(anyLong()))
                            .thenReturn(route.getDriver());

            Mockito
                    .when(plannerService.getOne(anyLong()))
                            .thenReturn(route.getPlanner());

            Mockito
                    .when(routeRepository.save(any(Route.class)))
                    .thenReturn(route);

            Route expected = routeService.patchRoute(1L, route);

            assertEquals(expected.getOrders(), route.getOrders());
            assertEquals(expected.getDriver(), route.getDriver());
            assertEquals(expected.getPlanner(), route.getPlanner());
            assertEquals(expected.getTruck(), route.getTruck());

    }






}