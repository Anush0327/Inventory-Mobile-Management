package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;

import com.example.inventorynumbermanagementapi.entity.Reservation;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
    }

    @Test
    void testReservationSettersAndGetters() {
        reservation.setId(1);
        reservation.setPhoneNumber("1234567890");
        reservation.setProvider("Provider XYZ");
        reservation.setConnectionType("Type A");
        reservation.setReservationDateTime(LocalDateTime.now());

        assertEquals(1, reservation.getId());
        assertEquals("1234567890", reservation.getPhoneNumber());
        assertEquals("Provider XYZ", reservation.getProvider());
        assertEquals("Type A", reservation.getConnectionType());
        assertNotNull(reservation.getReservationDateTime());
    }

    @Test
    void testReservationEquals() {
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);

        Reservation reservation2 = new Reservation();
        reservation2.setId(2);

        assertEquals(reservation, reservation);
        assertEquals(reservation1, reservation1);
        assertNotEquals(reservation, reservation1);
        assertNotEquals(reservation1, reservation2);
    }

    @Test
    void testReservationHashCode() {
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setPhoneNumber("1234567890");
        reservation1.setProvider("Provider XYZ");
        reservation1.setConnectionType("Type A");


        Reservation reservation2 = new Reservation();
        reservation2.setId(1);
        reservation2.setPhoneNumber("1234567890");
        reservation2.setProvider("Provider XYZ");
        reservation2.setConnectionType("Type A");

        assertEquals(reservation1.hashCode(), reservation2.hashCode());
    }

}
