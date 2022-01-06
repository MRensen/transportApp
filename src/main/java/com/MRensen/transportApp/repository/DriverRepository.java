package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {

}