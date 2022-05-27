package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

    @Test
    void setPaymentMethod() {
        PaymentMethod p = new PaymentMethod();
        p.setPaymentMethod("credit card");
        p.setCardName("Eduardo Nicolalde");
        p.setCardNumber("111222");
        p.setExpDate("03/22");
        p.setCvv("212");
        p.getPaymentMethod();
        p.getCardName();
        p.getCardNumber();
        p.getCvv();
        p.getExpDate();
    }
}