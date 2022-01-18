package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.AttributeOverrideException;
import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.DriverRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements UserService<Driver> {
    private DriverRepository driverRepository;
    private RouteRepository routeRepository;
    private UserRepository userRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository,
                         RouteRepository routeRepository,
                         UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver getOne(Long id) {
        Optional<Driver> driverOption = driverRepository.findById(id);
        if (driverOption.isPresent()) {
            return driverOption.get();
        } else {
            throw new RecordNotFoundException("Driver Id was not found");
        }
    }

    public Driver addOne(Driver driver) {
        userRepository.save(driver.getUser());
        return driverRepository.save(driver);
    }

    public void deleteOne(Long id) {
        Driver driver = driverRepository.getById(id);
        var routes = driver.getRoutes();
        for(Route route : routes){
            route.setDriver(null);
        }
//        driver.setRoute(null);
//        driverRepository.save(driver);
        driverRepository.deleteById(id);
    }

    public Driver patchOne(Long id, Driver driver) {
        if (!driverRepository.existsById(id)) {
            throw new RecordNotFoundException("Driver not found");
        }
        Driver old = driverRepository.findById(id).orElse(null);
        if (driver.getUser().getFirstName() != null) {
            old.getUser().setFirstName(driver.getUser().getFirstName());
        }
        if (driver.getUser().getLastName() != null) {
            old.getUser().setLastName(driver.getUser().getLastName());
        }
        if(driver.getUser().getCountry()!= null){
            old.getUser().setCountry(driver.getUser().getCountry());
        }
        if (driver.getUser().getStreet() != null) {
            old.getUser().setStreet(driver.getUser().getStreet());
        }
        if (driver.getUser().getHouseNumber() != null) {
            old.getUser().setHouseNumber(driver.getUser().getHouseNumber());
        }
        if (driver.getUser().getCity() != null) {
            old.getUser().setCity(driver.getUser().getCity());
        }
        if (driver.getEmployeeNumber() > 0) {
            old.setEmployeeNumber(driver.getEmployeeNumber());
        }
        if (driver.getDriverLicenseNumber() != null) {
            old.setDriverLicenseNumber(driver.getDriverLicenseNumber());
        }
        if (driver.getUser().getPhoneNumber() != null) {
            old.getUser().setPhoneNumber(driver.getUser().getPhoneNumber());
        }
        if (driver.getRegularTruck() != null) {
            old.setRegularTruck(driver.getRegularTruck());
        }
        if(driver.getUser().getUsername() != null){
            old.getUser().setUsername(driver.getUser().getUsername());
        }
        driverRepository.save(old);
        return old;
    }

    public String getPassword(Long id){
        if(driverRepository.existsById(id)) {
            Driver driver =driverRepository.getById(id);
            return driver.getUser().getPassword();
        } else {throw new RecordNotFoundException("Driver not found");}
    }

    public Driver updateOne(Long id, Driver driver) {
        if (!driverRepository.existsById(id)) {
            throw new RecordNotFoundException("Driver not found");
        }
        Driver old = driverRepository.findById(id).orElse(null);
        old.getUser().setFirstName(driver.getUser().getFirstName());
        old.getUser().setCountry(driver.getUser().getCountry());
        old.getUser().setLastName(driver.getUser().getLastName());
        old.getUser().setStreet(driver.getUser().getStreet());
        old.getUser().setHouseNumber(driver.getUser().getHouseNumber());
        old.getUser().setCity(driver.getUser().getCity());
        old.setEmployeeNumber(driver.getEmployeeNumber());
        old.setDriverLicenseNumber(driver.getDriverLicenseNumber());
        old.getUser().setPhoneNumber(driver.getUser().getPhoneNumber());
        old.setRegularTruck(driver.getRegularTruck());
        old.getUser().setUsername(driver.getUser().getUsername());
        driverRepository.save(old);
        return old;
    }

    public List<Route> getDriverRoute(Long id) {
        Optional<Driver> driverOption = driverRepository.findById(id);
        if (driverOption.isPresent()) {
            Driver driver = driverOption.get();
            return driver.getRoutes();
        } else {
            throw new RecordNotFoundException("Driver not found");
        }
    }

    public void addDriverRoute(Long route, Long id) {
        Optional<Driver> driverOption = driverRepository.findById(id);
        if (driverOption.isPresent()) {
            Driver driver = driverOption.get();
            Route routedb = routeRepository.findById(route).orElse(null);
            routedb.setDriver(driver);
            routeRepository.save(routedb);
            driver.addRoute(routedb);
            driverRepository.save(driver);
        } else {
            throw new RecordNotFoundException("Driver not found");
        }
    }

    public void deleteDriverRoute(Long driverId, Long routeId) {
        Optional<Driver> driverOption = driverRepository.findById(driverId);
        if (driverOption.isPresent()) {
            Driver driver = driverOption.get();
            List<Route> routes = new ArrayList(driver.getRoutes());
//            routes.removeIf(r -> routeId.equals(r.getId()));
            routes.stream().forEach((r) -> {
                if(routeId.equals(r.getId())) {
                    r.setDriver(null);
                    driver.deleteRoute(r);
                    routeRepository.save(r);
                }
            });
            driver.setRoutes(routes);
            driverRepository.save(driver);
        } else {
            throw new RecordNotFoundException("Driver not found");
        }

    }
}