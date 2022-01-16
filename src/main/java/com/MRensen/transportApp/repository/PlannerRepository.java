package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}