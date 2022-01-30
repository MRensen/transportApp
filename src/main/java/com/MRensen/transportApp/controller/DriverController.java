package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.DriverDto;
import com.MRensen.transportApp.DTO.RouteDto;
import com.MRensen.transportApp.exception.BadRequestException;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        var drivers = driverService.getAll().stream().map(DriverDto::fromDriver).toList();
        return ResponseEntity.ok().body(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Long id){
        var driver = DriverDto.fromDriver(driverService.getOne(id));
        return ResponseEntity.ok().body(driver);
    }

    @GetMapping("/{id}/route")
    public ResponseEntity<Object> getRoute(@PathVariable Long id){
        var routes = driverService.getDriverRoute(id).stream().map(RouteDto::fromRoute).toList();
        return ResponseEntity.ok().body(routes);
    }

    @PostMapping("")
    public ResponseEntity<Object> postDriver(@RequestBody DriverDto driver){
        if(driver.username == null){
            //TODO test
            throw new BadRequestException("Bad request: driver requires a username");
        }
        Driver newDriver = driverService.addOne(driver.toDriver());
        Long id = newDriver.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/password")
    public ResponseEntity<Object> getPassword(@PathVariable long id){
        String password = driverService.getPassword(id);
        return ResponseEntity.ok().body(password);
    }

    @PutMapping("/{id}/{route}")
    public ResponseEntity<Object> postRoute(@PathVariable Long id, @PathVariable Long route){
        driverService.addDriverRoute(route, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody DriverDto driver){
        driverService.updateOne(id, driver.toDriver());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Driver> patchDriver(@PathVariable Long id, @RequestBody DriverDto driver){
        driverService.patchOne(id, driver.toDriver());
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/{route}")
    public ResponseEntity<Object> deleteRoute(@PathVariable Long id,@PathVariable Long route){
        driverService.deleteDriverRoute(id, route);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDriver(@PathVariable Long id){
        driverService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

}