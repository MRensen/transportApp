package com.MRensen.transportApp.service;


import com.MRensen.transportApp.DTO.OrderDto;
import com.MRensen.transportApp.exception.RecordNotFoundException;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
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
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setId(2L);
        order2.setId(3L);
        Driver driver = new Driver();
        driver.setId(10L);
        driver.setRoutes(new ArrayList<Route>());
        Planner planner = new Planner();
        planner.setId(20L);
        orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        route = new Route("97-bph-8", driver, planner, orderList);
        route.setId(1L);
        order2.setRoute(route);
        order1.setRoute(route);
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
    void getRouteReturnsRouteOrException(){
        when(routeRepository.findById(route.getId()))
                .thenReturn(Optional.of(route));
        var actual = routeService.getRoute(route.getId());

        assertEquals("97-bph-8", actual.getTruck());
        assertThrows(RecordNotFoundException.class,
                ()->{routeService.getRoute(route.getId()+1);},
                "Route Id was not found");
    }

    @Test
    void addRouteReturnsRouteAndInvokesPatchOrder(){

        route.setOrders(orderList);

        Mockito
                .when(routeRepository.save(route))
                .thenReturn(route);
        Mockito
                .doNothing()
                .when(driverService).addDriverRoute(anyLong(), anyLong());
        Mockito
                .doNothing()
                .when(plannerService).addPlannerRoute(anyLong(), anyLong());
        Mockito
                .when(orderService.patchOrder(anyLong(), any()))
                .thenReturn(new Order());

        var actual = routeService.addRoute(route);

        assertEquals("97-bph-8", actual.getTruck());
        assertEquals(orderList, actual.getOrders());
        Mockito.verify(routeRepository, times(2)).save(route);
        Mockito.verify(orderService,times(route.getOrders().size())).patchOrder(anyLong(),any());

    }

    @Test
    void deleteRouteInvokesDeleteByIdAndPatchOrderOrException(){
        route.setOrders(orderList);

        Mockito
                .when(routeRepository.findById(route.getId()))
                .thenReturn(Optional.of(route));

        Mockito
                .when(driverService.patchOne(anyLong(), any(Driver.class)))
                .thenReturn(route.getDriver());

        Mockito
                .when(plannerService.patchOne(anyLong(), any(Planner.class)))
                .thenReturn(route.getPlanner());

        Mockito
                .when(orderService.patchOrder(anyLong(), any(Order.class)))
                .thenReturn(orderList.get(0));

        routeService.deleteRoute(route.getId());
        Mockito.verify(routeRepository, times(1)).deleteById(route.getId());
        Mockito.verify(orderService, times(route.getOrders().size())).patchOrder(anyLong(),any());
        assertThrows(RecordNotFoundException.class,
                ()->{routeService.deleteRoute(route.getId()+1);},
                "Route not found");
    }

    @Test
    void updateRouteTest(){
        Mockito
                .when(routeRepository.findById(route.getId()))
                .thenReturn(Optional.of(route));

        Mockito
                .when(routeRepository.existsById(route.getId()))
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
        assertThrows(RecordNotFoundException.class,
                ()->{routeService.updateRoute(route.getId()+1, expectedRoute);},
                "Route not found");
    }

    @Test
    void patchRouteTest(){

        route.setOrders(orderList);

            Mockito
                    .when(routeRepository.existsById(route.getId()))
                    .thenReturn(true);

            Mockito
                    .when(routeRepository.findById(route.getId()))
                    .thenReturn(Optional.of(new Route()));

            Mockito
                    .when(driverService.getOne(anyLong()))
                            .thenReturn(route.getDriver());

            Mockito
                    .when(plannerService.getOne(anyLong()))
                            .thenReturn(route.getPlanner());

            Mockito
                    .when(routeRepository.save(any(Route.class)))
                    .thenReturn(route);

            Mockito
                    .when(orderService.patchOrder(anyLong(), any()))
                    .thenReturn(new Order());

            Route expected = routeService.patchRoute(route.getId(), route);

            assertEquals(expected.getOrders(), orderList);
            assertEquals(expected.getDriver(), route.getDriver());
            assertEquals(expected.getPlanner(), route.getPlanner());
            assertEquals(expected.getTruck(), route.getTruck());
            assertEquals(route.getOrders(), orderList);
            Mockito.verify(orderService, times(orderList.size())).patchOrder(anyLong(),any());
            assertThrows(RecordNotFoundException.class,
                ()->{routeService.patchRoute(route.getId()+1, route);},
                "Route not found");

    }
    @Test
    void deleteOrdersInvokesSaveAndPatchOrderOrException(){
        route.setOrders(orderList);
        Mockito
                .when(routeRepository.getById(route.getId()))
                .thenReturn(route);
        Mockito
                .when(routeRepository.existsById(route.getId()))
                .thenReturn(true);
        Mockito
                .when(orderService.getOrder(anyLong()))
                .thenReturn(new Order());
        Mockito
                .when(orderService.patchOrder(anyLong(), any()))
                .thenReturn(new Order());
        Mockito
                .when(routeRepository.save(route))
                .thenReturn(route);

        routeService.deleteOrders(route.getId(), orderList.toArray(Order[]::new) );

        Mockito.verify(orderService, times(orderList.size())).patchOrder(anyLong(), any());
        assertThrows(RecordNotFoundException.class,
                ()->{routeService.deleteOrders(route.getId()+1, orderList.toArray(Order[]::new));},
                "Route not found");
    }






}