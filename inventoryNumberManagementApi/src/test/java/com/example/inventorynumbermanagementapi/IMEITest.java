package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;

import com.example.inventorynumbermanagementapi.entity.IMEI;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class IMEITest {

    private IMEI imei;

    @BeforeEach
    void setUp() {
        imei = new IMEI();
    }

    @Test
    void testIMEISettersAndGetters() {
        imei.setId(1);
        imei.setImeiNumber("123456789012345");

        assertEquals(1, imei.getId());
        assertEquals("123456789012345", imei.getImeiNumber());
    }

    @Test
    void testIMEIEquals() {
        IMEI imei1 = new IMEI();
        imei1.setId(1);

        IMEI imei2 = new IMEI();
        imei2.setId(2);

        assertEquals(imei, imei);
        assertEquals(imei1, imei1);
        assertNotEquals(imei, imei1);
        assertNotEquals(imei1, imei2);
    }

    @Test
    void testIMEIHashCode() {
        IMEI imei1 = new IMEI();
        imei1.setId(1);
        imei1.setImeiNumber("123456789012345");

        IMEI imei2 = new IMEI();
        imei2.setId(1);
        imei2.setImeiNumber("123456789012345");

        assertEquals(imei1.hashCode(), imei2.hashCode());
        
    }

}
