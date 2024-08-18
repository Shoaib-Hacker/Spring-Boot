package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.model.CustomerLogin;

public interface CustomerLoginRepository extends JpaRepository<CustomerLogin, Integer> {
    
}
