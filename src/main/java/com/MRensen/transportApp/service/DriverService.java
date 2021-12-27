package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.AttributeOverrideException;
import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
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

    public void deleteOne(Long id){driverRepository.deleteById(id);}

    public void updateOne(Long id, Driver driver){
        if(!driverRepository.existsById(id)){
            throw new RecordNotFoundException("Driver not found");
        }
        Driver old = driverRepository.findById(id).orElse(null);
        old.setId(customer.getId());
        old.setAdress(customer.getAdress());
        old.setName(customer.getName());
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
                driver.setRoute(route);
                driverRepository.save(driver);
            } else {
                throw new AttributeOverrideException("Attribute is allready filled");
            }
        } else { throw new RecordNotFoundException("Driver not found");}
    }
}