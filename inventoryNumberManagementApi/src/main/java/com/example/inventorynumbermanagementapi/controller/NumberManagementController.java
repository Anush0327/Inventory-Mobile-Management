package com.example.inventorynumbermanagementapi.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventorynumbermanagementapi.dto.InsertSimDTO;
import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.dto.SimDTO;
import com.example.inventorynumbermanagementapi.entity.Reservation;
import com.example.inventorynumbermanagementapi.entity.SIM;
import com.example.inventorynumbermanagementapi.service.InventoryService;

@RestController
@CrossOrigin
@RequestMapping("/api/number")
public class NumberManagementController {
    
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/newconnection")
    public ResponseEntity<Boolean> numberReservation(@RequestBody ReservationDTO reservationDTO){
        System.out.println(reservationDTO);
        return ResponseEntity.ok(inventoryService.issueInitiation(reservationDTO));
    }

    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveTheNumber(@RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(inventoryService.reserveTheNumber(reservationDTO));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservationsOf(@RequestParam String customerName){
        List<Reservation> reservations = inventoryService.getAllReservationsOf(customerName);
        if(reservations!=null){
            return ResponseEntity.ok(reservations);
        }
        else{
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/allreservations")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = inventoryService.getAllReservations();
        if(reservations==null)
            return ResponseEntity.ok(Collections.emptyList());
        else
            return ResponseEntity.ok(reservations);
    }

    @GetMapping("/allprepaidsims")
    public ResponseEntity<List<SimDTO>> getAllPrepaidSims(){
        List<SIM> sims = inventoryService.getAllPrepaidSims();
        List<SimDTO> simDTOs = inventoryService.convertToDTO(sims);
        if(sims!=null)
            return ResponseEntity.ok(simDTOs);
        else
            return ResponseEntity.ok(Collections.emptyList());
    }
    

    @GetMapping("/allpostpaidsims")
    public ResponseEntity<List<SimDTO>> getAllPostpaidSims(){
        List<SIM> sims = inventoryService.getAllPostpaidSims();
        List<SimDTO> simDTOs = inventoryService.convertToDTO(sims);
        if(sims!=null)
            return ResponseEntity.ok(simDTOs);
        else
            return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping("/replacesim")
    public ResponseEntity<Boolean> replaceSim(@RequestBody ReservationDTO reservationDTO){
        Boolean status = inventoryService.replaceSim(reservationDTO);
        if(status){
            return ResponseEntity.ok(status);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/insertSim")
    public ResponseEntity<Boolean> insertSimInto(@RequestBody InsertSimDTO insertSimDTO){
        System.out.println(insertSimDTO);
        Boolean status = inventoryService.setSIMInPhoneWithIMEI(insertSimDTO.getImei(), insertSimDTO.getMsisdn());
        return ResponseEntity.ok(status);
    }

    @PostMapping("/changeProvider")
    public ResponseEntity<Boolean> changeProvider(@RequestBody ReservationDTO reservationDTO){
        Boolean status = inventoryService.changeProvider(reservationDTO);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/inactivesims")
    public ResponseEntity<List<SimDTO>> getInactiveSims(){
        List<SIM> sims = inventoryService.getInactiveSims();
        if(sims!=null){
            List<SimDTO> simDTOs = inventoryService.convertToDTO(sims);
            return ResponseEntity.ok(simDTOs);
        }
        else{
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
