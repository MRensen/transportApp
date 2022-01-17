package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.RouteDto;
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
    public ResponseEntity<List<RouteDto>> getRoutes(){
        var routes = routeService.getAllRoutes().stream().map(RouteDto::fromRoute).toList();
        return ResponseEntity.ok().body(routes);
    }

    @GetMapping("{id}")
    public ResponseEntity<RouteDto> getRoute(@PathVariable Long id){
        RouteDto route = RouteDto.fromRoute(routeService.getRoute(id));
        return ResponseEntity.ok().body(route);
    }

    @PostMapping("")
    public ResponseEntity<Object> postRoute(@RequestBody RouteDto route){
        Route newRoute = routeService.addRoute(route.toRoute());
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
    public ResponseEntity<Object> putRoute(@PathVariable Long id, @RequestBody RouteDto route){
        routeService.updateRoute(id, route.toRoute());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/orders")
    public ResponseEntity<Object> deleteORders(@PathVariable Long id, @RequestBody String[] orders){
        routeService.deleteOrders(id, orders);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patchRoute(@PathVariable Long id, @RequestBody RouteDto route){
        routeService.patchRoute(id, route.toRoute());
        return ResponseEntity.noContent().build();
    }
}