package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Delivery;
import com.eduanico.resourceserv.web.model.Order;

public interface OrderService {

    Order createOrder(Long checkoutId, Delivery delivery);

}
