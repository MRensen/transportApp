package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}