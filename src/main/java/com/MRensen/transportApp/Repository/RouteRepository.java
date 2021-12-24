package com.MRensen.transportApp.Repository;

import com.MRensen.transportApp.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}