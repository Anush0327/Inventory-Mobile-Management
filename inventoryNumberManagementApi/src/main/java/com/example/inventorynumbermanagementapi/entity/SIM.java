package com.example.inventorynumbermanagementapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class SIM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    private MSISDN msisdn;

    @OneToOne
    private IMEI imei;

    @OneToOne
    private ICCID iccid;

    private LocalDateTime IssuedDateTime;
    
    private String simType;

    private boolean isActivated;
}
