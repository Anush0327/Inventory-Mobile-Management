package com.example.inventorynumbermanagementapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import com.example.inventorynumbermanagementapi.entity.Customer;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void testCustomerSettersAndGetters() {
        customer.setId(1);
        customer.setName("John Doe");
        customer.setAadharUID("123456789012");

        assertEquals(1, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("123456789012", customer.getAadharUID());
    }

    @Test
    void testCustomerEquals() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");
        customer1.setAadharUID("123456789012");

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("John Doe");
        customer2.setAadharUID("123456789012");

        Customer customer3 = new Customer();
        customer3.setId(2);
        customer3.setName("Jane Smith");
        customer3.setAadharUID("987654321098");

        assertEquals(customer1, customer2, "customer1 should equal customer2");
        assertNotEquals(customer1, customer3, "customer1 should not equal customer3");
    }

    
    @Test
    void testCustomerHashCode() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");
        customer1.setAadharUID("123456789012");

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("John Doe");
        customer2.setAadharUID("123456789012");

        int hashCode1 = customer1.hashCode();
        int hashCode2 = customer2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal for equal objects.");
    }
}
