package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;

public class RouteDto {
    public Long id;
    public String truck;
    @JsonIncludeProperties("id")
    public Driver driver;
    @JsonIncludeProperties("id")
    public Planner planner;
    @JsonIncludeProperties("id")
    public List<Order> orders;

    public static RouteDto fromRoute(Route r){
        RouteDto dto = new RouteDto();
        dto.id = r.getId();
        dto.truck = r.getTruck();
        dto.driver = r.getDriver();
        dto.planner = r.getPlanner();
        dto.orders = r.getOrders();
        return dto;
    }

    public Route toRoute(){
        Route r = new Route();
        r.setId(id);
        r.setTruck(truck);
        r.setDriver(driver);
        r.setPlanner(planner);
        r.setOrders(orders);
        return r;
    }
}