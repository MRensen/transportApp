package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.AttributeOverrideException;
import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.DriverRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private DriverRepository driverRepository;
    private RouteRepository routeRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository,
                         RouteRepository routeRepository) {
        this.driverRepository = driverRepository;
        this.routeRepository = routeRepository;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Driver getOne(Long id){
        Optional<Driver> driverOption = driverRepository.findById(id);
        if(driverOption.isPresent()) {
            return driverOption.get();
        } else {
            throw new RecordNotFoundException("Driver Id was not found");
        }}

    public Driver addOne(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteOne(Long id){
//        Driver driver = driverRepository.getById(id);
//        driver.setRoute(null);
//        driverRepository.save(driver);
        driverRepository.deleteById(id);
    }

    public Driver patchOne(Long id, Driver driver){
        if(!driverRepository.existsById(id)){
            throw new RecordNotFoundException("Driver not found");
        }
        Driver old = driverRepository.findById(id).orElse(null);
        if(driver.getFirstName() != null){
            old.setFirstName(driver.getFirstName());
        }
        if(driver.getLastName() != null){
            old.setLastName(driver.getLastName());
        }
        if(driver.getStreet() != null){
            old.setStreet(driver.getStreet());
        }
        if(driver.getHouseNumber() != null){
            old.setHouseNumber(driver.getHouseNumber());
        }
        if(driver.getCity() != null){
            old.setCity(driver.getCity());
        }
        if(driver.getEmployeeNumber() > 0){
            old.setEmployeeNumber(driver.getEmployeeNumber());
        }
        if(driver.getDriverLicenseNumber() != null){
            old.setDriverLicenseNumber(driver.getDriverLicenseNumber());
        }
        if(driver.getPhoneNumber() != null){
            old.setPhoneNumber(driver.getPhoneNumber());
        }
        if(driver.getRegularTruck() != null){
            old.setRegularTruck(driver.getRegularTruck());
        }
        driverRepository.save(old);
        return old;
    }

    public Driver updateOne(Long id, Driver driver){
        if(!driverRepository.existsById(id)){
            throw new RecordNotFoundException("Driver not found");
        }
        Driver old = driverRepository.findById(id).orElse(null);
        old.setFirstName(driver.getFirstName());
        old.setLastName(driver.getLastName());
        old.setStreet(driver.getStreet());
        old.setHouseNumber(driver.getHouseNumber());
        old.setCity(driver.getCity());
        old.setEmployeeNumber(driver.getEmployeeNumber());
        old.setDriverLicenseNumber(driver.getDriverLicenseNumber());
        old.setPhoneNumber(driver.getPhoneNumber());
        old.setRegularTruck(driver.getRegularTruck());
        driverRepository.save(old);
        return old;
    }

    public Route getDriverRoute(Long id){
        Optional<Driver> driverOption = driverRepository.findById(id);
        if(driverOption.isPresent()) {
            Driver driver = driverOption.get();
            return driver.getRoute();
        } else { throw new RecordNotFoundException("Driver not found");}
    }

    public void addDriverRoute(Route route, Long id){
        Optional<Driver> driverOption = driverRepository.findById(id);
        if(driverOption.isPresent()) {
            Driver driver = driverOption.get();
            if(driver.getRoute() == null){
                Route routedb = routeRepository.findById(route.getId()).orElse(null);
                routedb.setDriver(driver);
                routeRepository.save(routedb);
                driver.setRoute(routedb);
                driverRepository.save(driver);
            } else {
                throw new AttributeOverrideException("Attribute is allready filled");
            }
        } else { throw new RecordNotFoundException("Driver not found");}
    }

    public void deleteDriverRoute(Long id){
        Optional<Driver> driverOption = driverRepository.findById(id);
        if(driverOption.isPresent()) {
            Driver driver = driverOption.get();
            Route route = driver.getRoute();
            route.setDriver(null);
            routeRepository.save(route);
            driver.setRoute(null);
            driverRepository.save(driver);
        } else { throw new RecordNotFoundException("Driver not found");}

    }
}