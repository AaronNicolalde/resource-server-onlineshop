package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void testCheckout(){
        Checkout checkout = new Checkout("eduanico", new ArrayList<>(),10.0);
    }

}