package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Route>> getRoutes(){
        var routes = routeService.getAllRoutes();
        return ResponseEntity.ok().body(routes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Route> getRoute(@PathVariable Long id){
        Route route = routeService.getRoute(id);
        return ResponseEntity.ok().body(route);
    }

    @PostMapping("")
    public ResponseEntity<Object> postRoute(@RequestBody Route route){
        Route newRoute = routeService.addRoute(route);
        Long id = newRoute.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRoute(@PathVariable Long id){
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putRoute(@PathVariable Long id, @RequestBody Route route){
        routeService.updateRoute(id, route);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patchRoute(@PathVariable Long id, @RequestBody Route route){
        routeService.patchRoute(id, route);
        return ResponseEntity.noContent().build();
    }
}