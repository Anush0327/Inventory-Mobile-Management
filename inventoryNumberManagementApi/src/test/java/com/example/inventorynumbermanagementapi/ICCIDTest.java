package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;

import com.example.inventorynumbermanagementapi.entity.ICCID;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ICCIDTest {

    private ICCID iccid;

    @BeforeEach
    void setUp() {
        iccid = new ICCID();
    }

    @Test
    void testICCIDSettersAndGetters() {
        iccid.setId(1);
        iccid.setIccidNumber("1234567890123456789");
        iccid.setProvider("Provider XYZ");

        assertEquals(1, iccid.getId());
        assertEquals("1234567890123456789", iccid.getIccidNumber());
        assertEquals("Provider XYZ", iccid.getProvider());
    }

    @Test
    void testICCIDEquals() {
        ICCID iccid1 = new ICCID();
        iccid1.setId(1);

        ICCID iccid2 = new ICCID();
        iccid2.setId(2);

        assertEquals(iccid, iccid);
        assertEquals(iccid1, iccid1);
        assertNotEquals(iccid, iccid1);
        assertNotEquals(iccid1, iccid2);
    }

    @Test
    void testICCIDHashCode() {
        ICCID iccid1 = new ICCID();
        iccid1.setId(1);
        iccid1.setIccidNumber("1234567890123456789");
        iccid1.setProvider("Provider XYZ");

        ICCID iccid2 = new ICCID();
        iccid2.setId(1);
        iccid2.setIccidNumber("1234567890123456789");
        iccid2.setProvider("Provider XYZ");

        assertEquals(iccid1.hashCode(), iccid2.hashCode());
    }

}
