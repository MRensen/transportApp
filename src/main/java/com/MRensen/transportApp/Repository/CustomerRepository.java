package com.MRensen.transportApp.Repository;
import com.MRensen.transportApp.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository implements JpaRepository<Customer, Long> {

}