package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
