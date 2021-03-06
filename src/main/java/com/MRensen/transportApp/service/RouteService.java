package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private RouteRepository routeRepository;
    private OrderService orderService;
    private PlannerService plannerService;
    private DriverService driverService;

    @Autowired
    public RouteService(RouteRepository repository,
                        OrderService orderService,
                        PlannerService plannerService,
                        DriverService driverService) {
        this.routeRepository = repository;
        this.plannerService = plannerService;
        this.driverService = driverService;
        this.orderService = orderService;
    }

    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    public Route getRoute(Long id){
        Optional<Route> routeOption = routeRepository.findById(id);
        if(routeOption.isPresent()) {
            return routeOption.get();
        } else {
            throw new RecordNotFoundException("Route Id was not found");
        }}

    public Route addRoute(Route route) {
        routeRepository.save(route);
        driverService.addDriverRoute(route.getId(), route.getDriver().getId() );
        plannerService.addPlannerRoute(route.getId(), route.getPlanner().getId());
        Order order = new Order();
        order.setRoute(route);
        for(Order o : route.getOrders()) {
            orderService.patchOrder(o.getId(), order);
        }
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id){
        Optional<Route> routeOption = routeRepository.findById(id);
        if(routeOption.isPresent()){
            Route route = routeOption.get();
            Driver driver = route.getDriver();
            driver.deleteRoute(route);
            driverService.patchOne(driver.getId(), driver);
            Planner planner = route.getPlanner();
            planner.deleteRoute(route);
            plannerService.patchOne(planner.getId(), planner);
            List<Order> orders = route.getOrders();
            for(Order o : orders){
                if(id.equals(o.getRoute().getId())){
                    o.setRoute(null);
                    orderService.patchOrder(o.getId(), o);
                }
            }

        }else{
            throw new RecordNotFoundException("Route not found");
        }

        routeRepository.deleteById(id);
    }

    public Route updateRoute(Long id, Route route){
        if(!routeRepository.existsById(id)){
            throw new RecordNotFoundException("Route not found");
        }
        Route old = routeRepository.findById(id).orElse(null);

        old.setTruck(route.getTruck());
        old.setDriver(driverService.getOne(route.getDriver().getId()));
        old.setPlanner(plannerService.getOne(route.getPlanner().getId()));
        old.setOrders(route.getOrders());

        routeRepository.save(old);
        return old;
    }
    public Route patchRoute(Long id, Route route){
        if(!routeRepository.existsById(id)){
            throw new RecordNotFoundException("Route not found");
        }
        Route old = routeRepository.findById(id).orElse(null);

        if(route.getTruck() != null) {
            old.setTruck(route.getTruck());
        }
        if(route.getDriver() != null) {
           old.setDriver(driverService.getOne(route.getDriver().getId()));
        }
        if(route.getPlanner() != null) {
            old.setPlanner(plannerService.getOne(route.getPlanner().getId()));
        }
        if(route.getOrders() != null) {
            for(Order o: route.getOrders()){
                o.setRoute(old);
                orderService.patchOrder(o.getId(), o);
                old.addOrder(o);
            }
        }

        routeRepository.save(old);
        return old;
    }

    public void deleteOrders(Long id, Order[] orders){
        if (routeRepository.existsById(id)) {
            Route route = routeRepository.getById(id);
            for(Order order : orders){
                Order tempOrder = orderService.getOrder(order.getId());
                tempOrder.setRoute(null);
                orderService.patchOrder(order.getId(), tempOrder);
            }
            routeRepository.save(route);
        } else {
            throw new RecordNotFoundException("Route not found");
        }
    }
}