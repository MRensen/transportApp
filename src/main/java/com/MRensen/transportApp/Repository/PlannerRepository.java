package com.MRensen.transportApp.Repository;

import com.MRensen.transportApp.Model.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}