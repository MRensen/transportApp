package com.MRensen.transportApp.service;


import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Authority;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.PlannerRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public class PlannerServiceTest {

    @Autowired
    PlannerService plannerService;

    @MockBean
    PlannerRepository plannerRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RouteRepository routeRepository;

    Planner planner;
    User user;
    Route route;

    @BeforeEach
    void setup(){
        Set<Authority> authorities = null;

        List<Route> routes = new ArrayList<>();
        route = new Route();
        route.setId(2L);
        routes.add(route);

        user = new User("x", "planner", "x", "tester", "x", "x", "x", "x", "x", "x", "x", authorities);

        planner = new Planner();
        planner.setId(1L);
        planner.setUser(user);
        planner.setRoutes(routes);
    }

    @Test
    void getAllReturnsPlannerList(){
        List<Planner> planners = new ArrayList<>();
        planners.add(planner);

        Mockito
                .when(plannerRepository.findAll())
                .thenReturn(planners);

        List<Planner> actual = plannerService.getAll();

        assertEquals(planners, actual);
    }

    @Test
    void getOneThrowsException(){
        Mockito
                .when(plannerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{plannerService.getOne(1L);});
    }

    @Test
    void getOneReturnsPlanner(){
        Mockito
                .when(plannerRepository.findById(anyLong()))
                .thenReturn(Optional.of(planner));
        Planner actual = plannerService.getOne(1L);
        assertEquals("tester", actual.getUser().getUsername());
    }

    @Test
    void addOneReturnsPlanner(){
        Mockito
                .when(userRepository.save(any(User.class)))
                .thenReturn(user);
        Mockito
                .when(plannerRepository.save(any(Planner.class)))
                .thenReturn(planner);

        Planner actual = plannerService.addOne(planner);

        assertEquals("tester", actual.getUser().getUsername());
    }

    @Test
    void updateOneThrowsException(){
        Mockito
                .when(plannerRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()->{plannerService.updateOne(1L, planner);});
    }

    @Test
    void updateOneReturnsPlanner(){
        Planner planner1 = new Planner();
        User user1 = new User();
        planner1.setUser(user1);

        Mockito
                .when(plannerRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(plannerRepository.findById(anyLong()))
                .thenReturn(Optional.of(planner1));

        Planner actual = plannerService.updateOne(1L, planner);

        assertEquals("tester", actual.getUser().getUsername());
    }

    @Test
    void patchOneThrowsExceptionForMissingPlanner(){
        Mockito
                .when(plannerRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()->{plannerService.patchOne(1L, planner);});
    }

    @Test
    void patchOneReturnsPlanner(){
        Planner planner1 = new Planner();
        User user1 = new User();
        planner1.setUser(user1);

        Mockito
                .when(plannerRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(plannerRepository.findById(anyLong()))
                .thenReturn(Optional.of(planner1));
        Mockito
                .when(userRepository.existsById(anyString()))
                .thenReturn(true);
        Mockito
                .when(userRepository.getById(anyString()))
                .thenReturn(user);

        Planner actual = plannerService.patchOne(1L, planner);

        assertEquals("tester", actual.getUser().getUsername());
    }

    @Test
    void patchOneThrowsExceptionForMissingUser(){
        Planner planner1 = new Planner();
        User user1 = new User();
        planner1.setUser(user1);

        Mockito
                .when(plannerRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(plannerRepository.findById(anyLong()))
                .thenReturn(Optional.of(planner1));
        Mockito
                .when(userRepository.existsById(anyString()))
                .thenReturn(false);
        Mockito
                .when(userRepository.getById(anyString()))
                .thenReturn(user);

        assertThrows(RecordNotFoundException.class, ()->{plannerService.patchOne(1L, planner);});
    }

}