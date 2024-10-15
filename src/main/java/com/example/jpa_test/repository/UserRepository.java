package com.example.jpa_test.repository;

import com.example.jpa_test.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {

}