package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.PlannerRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlannerService implements UserServiceInt<Planner> {
    private PlannerRepository plannerRepository;
    private UserRepository userRepository;
    private RouteRepository routeRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository, UserRepository userRepository, RouteRepository routeRepository) {
        this.plannerRepository = plannerRepository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
    }


    public List<Planner> getAll(){
        return plannerRepository.findAll();
    }

    public Planner getOne(Long id){
        Optional<Planner> plannerOption = plannerRepository.findById(id);
        if(plannerOption.isPresent()) {
            return plannerOption.get();
        } else {
            throw new RecordNotFoundException("Planner Id was not found");
        }}

    public Planner addOne(Planner planner) {
        System.out.println(planner.getId());
        userRepository.save(planner.getUser());
        return plannerRepository.save(planner);
    }

    public void deleteOne(Long id){
        Optional<Planner> plannerOption = plannerRepository.findById(id);
        if(plannerOption.isPresent()){
            Planner planner = plannerOption.get();
            var routes = planner.getRoutes();
            for(Route r : routes){
                r.setPlanner(null);
                routeRepository.save(r);
            }
        }else{throw new RecordNotFoundException("planner not found");}
        plannerRepository.deleteById(id);}

    public Planner updateOne(Long id, Planner planner){
        if(!plannerRepository.existsById(id)){
            throw new RecordNotFoundException("Planner not found");
        }
        Planner old = plannerRepository.findById(id).orElse(null);
//
        old.getUser().setFirstName(planner.getUser().getFirstName());
        old.getUser().setLastName(planner.getUser().getLastName());
        old.getUser().setCountry(planner.getUser().getCountry());
        old.getUser().setStreet(planner.getUser().getStreet());
        old.getUser().setHouseNumber(planner.getUser().getHouseNumber());
        old.getUser().setCity(planner.getUser().getCity());
        old.getUser().setPhoneNumber(planner.getUser().getPhoneNumber());
        old.getUser().setUsername(planner.getUser().getUsername());
        old.getUser().setRole(planner.getUser().getRole());
        old.getUser().setPostalCode(planner.getUser().getPostalCode());
        old.setRoutes(planner.getRoutes());
        planner.getRoutes().stream().forEach(route -> route.setPlanner(old));


        plannerRepository.save(old);
        return old;
    }
    public Planner patchOne(Long id, Planner planner){
        if(!plannerRepository.existsById(id)){
            throw new RecordNotFoundException("Planner not found");
        }
        Planner old = plannerRepository.findById(id).orElse(null);
        if(planner.getUser().getRole() != null){
            old.getUser().setRole(planner.getUser().getRole());
        }
        if(planner.getUser().getCountry()!=null){
            old.getUser().setCountry(planner.getUser().getCountry());
        }
        if(planner.getUser().getFirstName()!= null) {
            old.getUser().setFirstName(planner.getUser().getFirstName());
        }
        if(planner.getUser().getLastName() != null) {
            old.getUser().setLastName(planner.getUser().getLastName());
        }
        if(planner.getUser().getStreet() != null) {
            old.getUser().setStreet(planner.getUser().getStreet());
        }
        if(planner.getUser().getHouseNumber() != null) {
            old.getUser().setHouseNumber(planner.getUser().getHouseNumber());
        }
        if(planner.getUser().getCity() != null) {
            old.getUser().setCity(planner.getUser().getCity());
        }
        if(planner.getUser().getPostalCode() != null){
            old.getUser().setPostalCode(planner.getUser().getPostalCode());
        }
        if(planner.getUser().getPhoneNumber() != null) {
            old.getUser().setPhoneNumber(planner.getUser().getPhoneNumber());
        }
        if(planner.getUser().getUsername() != null) {
            String username = planner.getUser().getUsername();
            if(userRepository.existsById(username)) {
                old.setUser(userRepository.getById(username));
            } else { throw new RecordNotFoundException("user not found");}
        }
        if(planner.getRoutes() != null) {
            old.setRoutes(planner.getRoutes());
            planner.getRoutes().stream().forEach(route -> route.setPlanner(old));
        }

        plannerRepository.save(old);
        return old;
    }

    public void addPlannerRoute(Long route, Long id) {
        Optional<Planner> plannerOption = plannerRepository.findById(id);
        if (plannerOption.isPresent()) {
            Planner planner = plannerOption.get();
            Route routedb = routeRepository.findById(route).orElse(null);
            routedb.setPlanner(planner);
            routeRepository.save(routedb);
            planner.addRoute(routedb);
            plannerRepository.save(planner);
        } else {
            throw new RecordNotFoundException("planner not found");
        }
    }
}