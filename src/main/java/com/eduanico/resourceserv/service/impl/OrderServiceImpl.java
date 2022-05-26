package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.repository.OrderRepository;
import com.eduanico.resourceserv.service.OrderService;
import com.eduanico.resourceserv.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CheckoutRepository checkoutRepository;

    @Autowired
    OrderRepository orderRepository;


    public Order createOrder(Long checkoutId){
        Checkout checkout = checkoutRepository.findByCheckoutId(checkoutId);
        if(checkout != null){
            Address address = checkout.getDeliveryAddress();
            PaymentMethod payment = checkout.getPaymentMethodSelected();
            double total = checkout.getTotal();
            String customerName = checkout.getUsername();
            Order order = new Order(checkoutId, customerName, checkout.getProductList(), new Delivery(address.getAddress(),payment.getPaymentMethod(), total));
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }


}
