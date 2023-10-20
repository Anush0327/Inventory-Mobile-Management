package com.example.inventorynumbermanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventorynumbermanagementapi.entity.ICCID;

public interface ICCIDRepository extends JpaRepository<ICCID,Integer>{
    
}
