package com.eduanico.resourceserv.web.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;

    @Test
    void testOrder(){
        order = new Order(1L,"eduanico",new ArrayList<>(),new Delivery());
        order.setOrderId(1L);
        order.setCustomerId(1L);
        order.setDelivery(new Delivery());
        order.setProductList(new ArrayList<>());
        order.setCustomerName("eduanico");
        order.getOrderId();
        order.getCustomerName();
        order.getCustomerId();
        order.getDelivery();
        order.getProductList();
    }

}