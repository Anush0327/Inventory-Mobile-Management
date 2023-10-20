package com.example.inventoryNumberManagementApi;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.entity.*;
import com.example.inventorynumbermanagementapi.repository.*;
import com.example.inventorynumbermanagementapi.service.InventoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class InventoryServiceTests {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MSISDNRepository msisdnRepository;

    @Mock
    private ICCIDRepository iccidRepository;

    @Mock
    private IMEIRepository imeiRepository;

    @Test
    public void testReservationInitiation() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("TestCustomer");
        reservationDTO.setProvider("TestProvider");
        reservationDTO.setReservingNumber("1234567890");
        reservationDTO.setProvider("AIRTEL");

        Customer customer = new Customer();
        customer.setName("TestCustomer");
        MSISDN existingMsisdn = new MSISDN();
        existingMsisdn.setMsisdnNumber("1234567890");


        when(customerRepository.findByName("TestCustomer")).thenReturn(Optional.of(customer));
        when(msisdnRepository.findByMsisdnNumber("1234567890")).thenReturn(Optional.of(existingMsisdn));

        boolean result = inventoryService.issueInitiation(reservationDTO);
        assertFalse(result);
        reservationDTO.setReservingNumber("9876543210");
        result = inventoryService.issueInitiation(reservationDTO);
        assertTrue(result);
    }

    @Test
    public void testActivate() {
        Customer customer = new Customer();
        customer.setName("Anush");
        customer.setSims(new ArrayList<>());

        SIM sim = new SIM();
        sim.setActivated(false);
        sim.setIssuedDateTime(LocalDateTime.now().minusDays(1));
        customer.getSims().add(sim);

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        boolean result = inventoryService.activate();

        assertEquals(true, result);
    }


    @Test
    public void testIMEIAlreadyExist() {
        Customer customer = new Customer();
        SIM sim = new SIM();
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");
        sim.setImei(imei);
        customer.setSims(List.of(sim));

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        boolean result = inventoryService.imeiAlreadyExist("123456789012345");
        assertEquals(true, result);
    }

    @Test
    public void testSetSIMInPhoneWithIMEI() {
        Customer customer = new Customer();
        SIM sim = new SIM();
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("9876543210");
        sim.setMsisdn(msisdn);
        customer.setSims(List.of(sim));

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(imeiRepository.save(any(IMEI.class))).thenReturn(new IMEI());

        boolean result = inventoryService.setSIMInPhoneWithIMEI("123456789012345", "9876543210");
        assertEquals(true, result);
    }
}
