package com.example.inventorynumbermanagementapi;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.example.inventorynumbermanagementapi.business.JsonUtil;
import com.example.inventorynumbermanagementapi.dto.InsertSimDTO;
import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.entity.Customer;
import com.example.inventorynumbermanagementapi.entity.Reservation;
import com.example.inventorynumbermanagementapi.entity.SIM;
import com.example.inventorynumbermanagementapi.repository.CustomerRepository;
import com.example.inventorynumbermanagementapi.service.InventoryService;

import jakarta.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class InventoryNumberManagementApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private CustomerRepository customerRepository;

    @Mock
    private ReservationDTO mockReservationDTO;

    @BeforeEach
    void setUp() {
        mockReservationDTO = new ReservationDTO();
        mockReservationDTO.setAadharUID("385446414996");
        mockReservationDTO.setCustomerName("Anush");
        mockReservationDTO.setProvider("AIRTEL");
        mockReservationDTO.setConnectionType("prepaid");
        mockReservationDTO.setReservingNumber("7075718283");
        mockReservationDTO.setLocation("Hyderabad");
    }

    @Test
    void testReserveNumber() throws Exception {
        when(inventoryService.reserveTheNumber(any(ReservationDTO.class))).thenReturn(true);
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/number/reserve")
               .contentType("application/json")
               .content(JsonUtil.toJson(mockReservationDTO)))
               .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);

    }

    @Test
    void testNumberReservation() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setAadharUID("123456789012");
        reservationDTO.setCustomerName("TestCustomer");
        reservationDTO.setProvider("TestProvider");
        reservationDTO.setReservingNumber("1234567890");

        when(inventoryService.issueInitiation(any(ReservationDTO.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/number/newconnection")
                .contentType("application/json")
                .content(JsonUtil.toJson(reservationDTO)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);
    }

    @Test
    void testGetAllPrepaidSims() throws Exception {
        List<SIM> sims = new ArrayList<>();

        when(inventoryService.getAllPrepaidSims()).thenReturn(sims);
        when(inventoryService.activate()).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/number/allprepaidsims"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
    }

    @Test
    void testGetAllPostpaidSims() throws Exception {
        List<SIM> sims = new ArrayList<>();

        when(inventoryService.getAllPostpaidSims()).thenReturn(sims);
        when(inventoryService.activate()).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/number/allpostpaidsims"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
    }

    @Test
    void testGetInactiveSims() throws Exception {
        List<SIM> sims = new ArrayList<>();

        when(inventoryService.getInactiveSims()).thenReturn(sims);
        when(inventoryService.activate()).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/number/inactivesims"))
               .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
    }

    @Test
    void testGetAllReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<>();

        when(inventoryService.getAllReservations()).thenReturn(reservations);
        when(inventoryService.activate()).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/number/allreservations"))
              .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
    }

    @Test
    void testChangeProvider() throws Exception {
        when(inventoryService.changeProvider(any(ReservationDTO.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/number/changeProvider")
                .contentType("application/json")
                .content(JsonUtil.toJson(mockReservationDTO)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);
    }
    

    @Test
    void testActivate() {
        Customer customer = new Customer();
        customer.setName("Anush");
        customer.setSims(new ArrayList<>());
        SIM sim = new SIM();
        sim.setActivated(false);
        sim.setIssuedDateTime(LocalDateTime.now().minusDays(1));
        customerRepository.save(customer);
        Boolean status = inventoryService.activate();
        assertEquals(false, status);
    }

    @Test
    void testInsertSimInto() throws Exception {
        InsertSimDTO insertSimDTO = new InsertSimDTO();
        insertSimDTO.setImei("123456789012345");
        insertSimDTO.setMsisdn("9876543210");

        when(inventoryService.setSIMInPhoneWithIMEI(any(String.class), any(String.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/number/insertSim")
                .contentType("application/json")
                .content(JsonUtil.toJson(insertSimDTO)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);
    }
}
