package com.example.inventorynumbermanagementapi.dto;

import lombok.Data;

@Data
public class SimDTO {
    private int id;
    private String msisdn;
    private String iccid;
    private String imei;
    private String issuedDateTime;
    private boolean isActivated;
    private String customerName;
    private String connectionType;
}
