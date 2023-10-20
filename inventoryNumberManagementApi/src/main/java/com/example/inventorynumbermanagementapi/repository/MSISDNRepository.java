package com.example.inventorynumbermanagementapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventorynumbermanagementapi.entity.MSISDN;

public interface MSISDNRepository extends JpaRepository<MSISDN,Integer>{
    Optional<MSISDN> findByMsisdnNumber(String msisdnNumber);
}
