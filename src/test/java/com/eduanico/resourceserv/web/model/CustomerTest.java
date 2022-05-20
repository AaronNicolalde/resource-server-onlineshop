package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;

    @Test
    void setCustomerId() {
        customer = new Customer();
        customer.setCustomerId(1L);
    }

    @Test
    void setUsername() {
        customer = new Customer();
        customer.setUsername("eduanico");
    }
}