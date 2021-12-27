package com.MRensen.transportApp.repository;
import com.MRensen.transportApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}