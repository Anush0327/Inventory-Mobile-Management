package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;

import com.example.inventorynumbermanagementapi.entity.MSISDN;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MSISDNTest {

    private MSISDN msisdn;

    @BeforeEach
    void setUp() {
        msisdn = new MSISDN();
    }

    @Test
    void testMSISDNSettersAndGetters() {
        msisdn.setId(1);
        msisdn.setMsisdnNumber("1234567890");

        assertEquals(1, msisdn.getId());
        assertEquals("1234567890", msisdn.getMsisdnNumber());
    }

    @Test
    void testMSISDNEquals() {
        MSISDN msisdn1 = new MSISDN();
        msisdn1.setId(1);

        MSISDN msisdn2 = new MSISDN();
        msisdn2.setId(2);

        assertEquals(msisdn, msisdn);
        assertEquals(msisdn1, msisdn1);
        assertNotEquals(msisdn, msisdn1);
        assertNotEquals(msisdn1, msisdn2);
    }

    @Test
    void testMSISDNHashCode() {
        MSISDN msisdn1 = new MSISDN();
        msisdn.setId(1);
        msisdn.setMsisdnNumber("1234567890");

        MSISDN msisdn2 = new MSISDN();
        msisdn.setId(1);
        msisdn.setMsisdnNumber("1234567890");

        assertEquals(msisdn1.hashCode(), msisdn2.hashCode());
    }
}
