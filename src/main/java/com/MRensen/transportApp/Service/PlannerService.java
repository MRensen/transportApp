package com.MRensen.transportApp.Service;

import com.MRensen.transportApp.Repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannerService {
    private PlannerRepository plannerRepository;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }
}