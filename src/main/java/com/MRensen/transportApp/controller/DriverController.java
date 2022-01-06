package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.DriverDto;
import com.MRensen.transportApp.DTO.RouteDto;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("")
    public ResponseEntity<List<DriverDto>> getDrivers(){
        var drivers = driverService.getAllDrivers().stream().map(DriverDto::fromDriver).toList();
        return ResponseEntity.ok().body(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable String username){
        var driver = DriverDto.fromDriver(driverService.getOne(username));
        return ResponseEntity.ok().body(driver);
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<RouteDto> getRoute(@PathVariable String id){
        var route = RouteDto.fromRoute(driverService.getDriverRoute(id));
        return ResponseEntity.ok().body(route);
    }

    @PostMapping("")
    public ResponseEntity<Object> postDriver(@RequestBody DriverDto driver){
        Driver newDriver = driverService.addOne(driver.toDriver());
        Long id = newDriver.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/route")
    public ResponseEntity<Object> postRoute(@PathVariable String id, @RequestBody RouteDto route){
        driverService.addDriverRoute(route.toRoute(), id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable String id, @RequestBody DriverDto driver){
        driverService.updateOne(id, driver.toDriver());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Driver> patchDriver(@PathVariable String id, @RequestBody DriverDto driver){
        driverService.patchOne(id, driver.toDriver());
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/route")
    public ResponseEntity<Object> deleteRoute(@PathVariable String id){
        driverService.deleteDriverRoute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDriver(@PathVariable String id){
        driverService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

}