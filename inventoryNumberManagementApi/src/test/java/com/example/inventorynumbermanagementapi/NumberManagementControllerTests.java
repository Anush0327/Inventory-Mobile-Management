package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.inventorynumbermanagementapi.controller.NumberManagementController;
import com.example.inventorynumbermanagementapi.dto.InsertSimDTO;
import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.entity.SIM;
import com.example.inventorynumbermanagementapi.service.InventoryService;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberManagementController.class)
public class NumberManagementControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    void testFunctionalNumberReservation() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("TestCustomer");
        reservationDTO.setProvider("TestProvider");
        reservationDTO.setReservingNumber("1234567890");

        when(inventoryService.issueInitiation(reservationDTO)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/number/newconnection")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerName\":\"TestCustomer\", \"provider\":\"TestProvider\", \"reservingNumber\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    void testFunctionalGetAllSims() throws Exception {
        SIM sim = new SIM();
        sim.setSimType("prepaid");
        List<SIM> sims = Collections.singletonList(sim);

        when(inventoryService.getAllPrepaidSims()).thenReturn(sims);
        when(inventoryService.activate()).thenReturn(true);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/number/allprepaidsims"));
        
                result.andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testFunctionalInsertSimInto() throws Exception {
        InsertSimDTO insertSimDTO = new InsertSimDTO();
        insertSimDTO.setImei("123456789012345");
        insertSimDTO.setMsisdn("9876543210");

        when(inventoryService.setSIMInPhoneWithIMEI(insertSimDTO.getImei(), insertSimDTO.getMsisdn())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/number/insertSim")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"imei\":\"123456789012345\", \"msisdn\":\"9876543210\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }
}

