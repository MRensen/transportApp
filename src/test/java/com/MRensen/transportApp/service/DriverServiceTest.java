package com.MRensen.transportApp.service;


import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Driver;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.DriverRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@ContextConfiguration(classes={TransportAppApplication.class})
@EnableConfigurationProperties
public class DriverServiceTest {

    @Autowired
    DriverService driverService;

    @MockBean
    DriverRepository driverRepository;

    @MockBean
    RouteRepository routeRepository;

    @MockBean
    UserRepository userRepository;

    Driver driver;


    Route route;


    User user;

    @BeforeEach
    void setup(){
        user = new User();
        route = new Route();
        driver = new Driver();
        user.setUsername("testman");
        user.setPassword("password");
        user.setFirstName("testy");
        user.setLastName("testicle");
        user.setCountry("testland");
        user.setStreet("teststraat");
        user.setHouseNumber("3");
        user.setCity("teststad");
        user.setPhoneNumber("3");

        driver.setUser(user);
        driver.setEmployeeNumber(3);
        driver.setDriverLicenseNumber("3");
        driver.setRegularTruck("3");
        List<Route> routes = new ArrayList<>();
        routes.add(route);
        driver.setRoutes(routes);
        driver.setId(1L);
    }

    @Test
    void getAllReturnsDriverList(){
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);
        Mockito
                .when(driverRepository.findAll())
                .thenReturn(drivers);
        var actual = driverService.getAll();
        assertEquals(drivers, actual);

    }

    @Test
    void getOneThrowsRecordNotFoundException(){
        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{driverService.getOne(1L);});
    }

    @Test
    void getOneReturnsDriver(){
        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.of(driver));

        Driver actual = driverService.getOne(1L);

        assertEquals("testman", actual.getUser().getUsername());
    }

    @Test
    void addOneReturnsDriver(){
        Mockito
                .when(userRepository.save(any(User.class)))
                .thenReturn(user);

        Mockito
                .when(driverRepository.save(any(Driver.class)))
                .thenReturn(driver);

        Driver actual = driverService.addOne(driver);

        assertEquals("testman", actual.getUser().getUsername());
    }

    @Test
    void patchOneReturnsDriver(){
        Driver driver2 = driver;
        driver2.getUser().setFirstName("jan");

        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.of(driver));

        Driver actual = driverService.patchOne(anyLong() ,driver2);

        assertEquals("testman", actual.getUser().getUsername());
        assertEquals("jan", actual.getUser().getFirstName());
    }

    @Test
    void patchOneThrowsException() {
        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()->{driverService.patchOne(1L, driver);});
    }

    @Test
    void getPasswordReturnsPassword(){
        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(driverRepository.getById(anyLong()))
                .thenReturn(driver);

        var actual = driverService.getPassword(1L);
        assertEquals("password", actual);
    }

    @Test
    void getPasswordThrowsException(){
        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(false);
        Mockito
                .when(driverRepository.getById(anyLong()))
                .thenReturn(driver);
        assertThrows(RecordNotFoundException.class, ()->{driverService.getPassword(1L);});

    }

    @Test
    void updateOneThrowsException(){
        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()->{driverService.updateOne(1L, driver);});
    }

    @Test
    void updateOneReturnsDriver(){
        User user1 = new User();
        user1.setUsername("user1");
        Driver driver1 = new Driver();
        driver1.setUser(user1);
        driver1.setId(2L);
        Mockito
                .when(driverRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.of(driver1));

        Driver actual = driverService.updateOne(1L, driver);

        assertEquals("testman", actual.getUser().getUsername());
        assertEquals(3, actual.getEmployeeNumber());
    }

    @Test
    void getDriverRouteThrowsException(){
        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{driverService.getDriverRoute(1L);});
    }

    @Test
    void getDriverRouteReturnsRouteList(){
        List<Route> routes = new ArrayList<>();
        routes.add(route);

        Mockito
                .when(driverRepository.findById(anyLong()))
                .thenReturn(Optional.of(driver));

        List<Route> actual = driverService.getDriverRoute(1L);

        assertEquals(routes, actual);
    }
}