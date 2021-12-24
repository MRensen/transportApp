package com.MRensen.transportApp.Service;

import com.MRensen.transportApp.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private RouteRepository repository;

    @Autowired
    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }
}