package com.example.inventorynumbermanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventorynumbermanagementapi.entity.IMEI;

public interface IMEIRepository extends JpaRepository<IMEI,Integer>{
    
}
