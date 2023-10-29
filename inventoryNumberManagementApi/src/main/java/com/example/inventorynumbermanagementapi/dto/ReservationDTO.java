package com.example.inventorynumbermanagementapi.dto;

import lombok.Data;

@Data
public class ReservationDTO {
    private String customerName;
    
    private String aadharUID;

    private String provider;

    private String reservingNumber;

    private String location;

    private String connectionType;

}
