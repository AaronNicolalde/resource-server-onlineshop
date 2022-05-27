package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;




class DeliveryTest {

    @Test
    void testDelivery(){
        Delivery delivery = new Delivery(new Address(), new PaymentMethod(), 10.0);
        delivery.getDeliveryId();
        delivery.getDeliveryAddress();
        delivery.getRegisterPayment();
        delivery.getRegisterPayment();
        delivery.getTotalPurchase();
        delivery.setDeliveryId(1L);
        delivery.setDeliveryAddress(new Address());
        delivery = new Delivery();
    }
}