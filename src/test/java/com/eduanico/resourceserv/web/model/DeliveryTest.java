package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class DeliveryTest {

    private Delivery delivery;

    @Test
    void testDelivery(){
        delivery = new Delivery("urb test","credit card", 10.0);
        delivery.getDeliveryId();
        delivery.getDeliveryAddress();
        delivery.getRegisterPayment();
        delivery.getRegisterPayment();
        delivery.getTotalPurchase();
        delivery.setDeliveryId(1L);
        delivery.setDeliveryAddress("new address");
        delivery = new Delivery();
    }
}