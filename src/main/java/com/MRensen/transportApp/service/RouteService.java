package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository repository) {
        this.routeRepository = repository;
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
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id){routeRepository.deleteById(id);}

    public void updateRoute(Long id, Route route){
        if(!routeRepository.existsById(id)){
            throw new RecordNotFoundException("Route not found");
        }
        Route old = routeRepository.findById(id).orElse(null);
        old.setId(customer.getId());
        old.setAdress(customer.getAdress());
        old.setName(customer.getName());
    }
}