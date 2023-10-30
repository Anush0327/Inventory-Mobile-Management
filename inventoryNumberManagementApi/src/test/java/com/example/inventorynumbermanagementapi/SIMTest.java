package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;

import com.example.inventorynumbermanagementapi.entity.ICCID;
import com.example.inventorynumbermanagementapi.entity.IMEI;
import com.example.inventorynumbermanagementapi.entity.MSISDN;
import com.example.inventorynumbermanagementapi.entity.SIM;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class SIMTest {

    private SIM sim;

    @BeforeEach
    void setUp() {
        sim = new SIM();
    }

    @Test
    void testSIMSettersAndGetters() {
        sim.setId(1);
        sim.setMsisdn(new MSISDN());
        sim.setImei(new IMEI());
        sim.setIccid(new ICCID());
        sim.setIssuedDateTime(LocalDateTime.now());
        sim.setSimType("Type A");
        sim.setActivated(true);

        assertEquals(1, sim.getId());
        assertNotNull(sim.getMsisdn());
        assertNotNull(sim.getImei());
        assertNotNull(sim.getIccid());
        assertNotNull(sim.getIssuedDateTime());
        assertEquals("Type A", sim.getSimType());
        assertTrue(sim.isActivated());
    }

    @Test
    void testSIMEquals() {
        SIM sim1 = new SIM();
        sim1.setId(1);

        SIM sim2 = new SIM();
        sim2.setId(2);

        assertEquals(sim, sim);
        assertEquals(sim1, sim1);
        assertNotEquals(sim, sim1);
        assertNotEquals(sim1, sim2);
    }

    @Test
    void testSIMHashCode() {
        SIM sim1 = new SIM();
        sim1.setId(1);
        sim1.setMsisdn(new MSISDN());
        sim1.setImei(new IMEI());
        sim1.setIccid(new ICCID());
        sim1.setSimType("Type A");
        sim1.setActivated(true);

        SIM sim2 = new SIM();
        sim2.setId(1);
        sim2.setMsisdn(new MSISDN());
        sim2.setImei(new IMEI());
        sim2.setIccid(new ICCID());
        sim2.setSimType("Type A");
        sim2.setActivated(true);

        assertEquals(sim1.hashCode(), sim2.hashCode());
    }

}
