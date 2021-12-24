package com.MRensen.transportApp.Repository;

import com.MRensen.transportApp.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}