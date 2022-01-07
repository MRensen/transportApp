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

    @GetMapping("/{username}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable String username){
        var driver = DriverDto.fromDriver(driverService.getOne(username));
        return ResponseEntity.ok().body(driver);
    }

    @GetMapping("/{username}/route")
    public ResponseEntity<Object> getRoute(@PathVariable String username){
        var routes = driverService.getDriverRoute(username).stream().map(RouteDto::fromRoute).toList();
        return ResponseEntity.ok().body(routes);
    }

    @PostMapping("")
    public ResponseEntity<Object> postDriver(@RequestBody DriverDto driver){
        Driver newDriver = driverService.addOne(driver.toDriver());
        String username = newDriver.getUsername();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(username).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{username}/route")
    public ResponseEntity<Object> postRoute(@PathVariable String username, @RequestBody RouteDto route){
        driverService.addDriverRoute(route.toRoute(), username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Driver> updateDriver(@PathVariable String username, @RequestBody DriverDto driver){
        driverService.updateOne(username, driver.toDriver());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{username}")
    public ResponseEntity<Driver> patchDriver(@PathVariable String username, @RequestBody DriverDto driver){
        driverService.patchOne(username, driver.toDriver());
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/route")
    public ResponseEntity<Object> deleteRoute(@PathVariable String username){
        driverService.deleteDriverRoute(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteDriver(@PathVariable String username){
        driverService.deleteOne(username);
        return ResponseEntity.noContent().build();
    }

}