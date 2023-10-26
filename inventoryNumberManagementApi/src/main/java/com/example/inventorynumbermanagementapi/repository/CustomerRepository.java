package com.example.inventorynumbermanagementapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventorynumbermanagementapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    Optional<Customer> findByName(String name);

    Optional<Customer> findByAadharUID(String aadharUID);
    
}
