package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Planner;
import com.MRensen.transportApp.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlannerService {
    private PlannerRepository plannerRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }


    public List<Planner> getAllPlanners(){
        return plannerRepository.findAll();
    }

    public Planner getPlanner(Long id){
        Optional<Planner> plannerOption = plannerRepository.findById(id);
        if(plannerOption.isPresent()) {
            return plannerOption.get();
        } else {
            throw new RecordNotFoundException("Planner Id was not found");
        }}

    public Planner addPlanner(Planner planner) {
        return plannerRepository.save(planner);
    }

    public void deletePlanner(Long id){plannerRepository.deleteById(id);}

    public void updatePlanner(Long id, Planner planner){
        if(!plannerRepository.existsById(id)){
            throw new RecordNotFoundException("Planner not found");
        }
        Planner old = plannerRepository.findById(id).orElse(null);
        old.setId(customer.getId());
        old.setAdress(customer.getAdress());
        old.setName(customer.getName());
    }
}