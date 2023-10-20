package com.example.inventorynumbermanagementapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventorynumbermanagementapi.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

    List<Reservation> findAllByCustomerId(Integer customerId);
    
}
