package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Long checkoutId);
    List<Order> listOrders();

}
