package com.example.inventoryNumberManagementApi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.inventorynumbermanagementapi.business.RandomICCID;
import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.entity.Customer;
import com.example.inventorynumbermanagementapi.entity.ICCID;
import com.example.inventorynumbermanagementapi.entity.IMEI;
import com.example.inventorynumbermanagementapi.entity.MSISDN;
import com.example.inventorynumbermanagementapi.entity.SIM;
import com.example.inventorynumbermanagementapi.repository.CustomerRepository;
import com.example.inventorynumbermanagementapi.repository.ICCIDRepository;
import com.example.inventorynumbermanagementapi.repository.IMEIRepository;
import com.example.inventorynumbermanagementapi.repository.MSISDNRepository;
import com.example.inventorynumbermanagementapi.service.InventoryService;

import jakarta.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class InventoryServiceIntegrationTests {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired 
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    @Autowired
    private ICCIDRepository iccidRepository;


    @Test
    public void testIntegrationReservationInitiation() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("TestCustomerB");
        reservationDTO.setProvider("TestProvider");
        reservationDTO.setReservingNumber("1234567890");

        Customer customer = new Customer();
        customer.setName("TestCustomerB");
        MSISDN existingMsisdn = new MSISDN();
        existingMsisdn.setMsisdnNumber("1234567890");
        msisdnRepository.save(existingMsisdn);
        customer.setSims(new ArrayList<>());
        SIM sim = new SIM();
        sim.setMsisdn(existingMsisdn);
        ICCID newIccid = new ICCID();
        newIccid.setIccidNumber(RandomICCID.generate("AIRTEL"));
        iccidRepository.save(newIccid);
        sim.setIccid(newIccid);
        customer.getSims().add(sim);

        customerRepository.save(customer);

        boolean result = inventoryService.issueInitiation(reservationDTO);
        assertFalse(result);
        customerRepository.delete(customer);
        msisdnRepository.delete(existingMsisdn);
        iccidRepository.delete(newIccid);
    }

    @Test
    public void testIntegrationActivate() {
        Customer customer = new Customer();
        customer.setName("Anush");
        customer.setSims(new ArrayList<>());

        SIM sim = new SIM();
        sim.setActivated(false);
        sim.setIssuedDateTime(LocalDateTime.now().minusDays(1));
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");
        iccidRepository.save(iccid);
        sim.setIccid(iccid);
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");
        msisdnRepository.save(msisdn);
        sim.setMsisdn(msisdn);
        customer.getSims().add(sim);

        customerRepository.save(customer);

        boolean result = inventoryService.activate();

        assertEquals(true, result);
        customerRepository.delete(customer);
    }

    @Test
    public void testIntegrationSetSIMInPhoneWithIMEI() {
        Customer customer = new Customer();
        SIM sim = new SIM();
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("9876543210");
        sim.setMsisdn(msisdn);
        msisdnRepository.save(msisdn);
        customer.setSims(List.of(sim));

        customerRepository.save(customer);
        imeiRepository.save(new IMEI());

        boolean result = inventoryService.setSIMInPhoneWithIMEI("123456789012345", "9876543210");
        assertEquals(true, result);
    }
}
