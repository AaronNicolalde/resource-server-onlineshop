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
        if(checkout!=null){
            String address = checkout.getAddress().get(0);
            String payment = checkout.getPaymentMethod().get(0);
            double total = checkout.getTotal();
            Customer customer = checkout.getCustomer();
            List<Product> productList = checkout.getProductList();
            Long customerId = customer.getCustomerId();
            String customerName = customer.getUsername();
            Order order = new Order(customerId, customerName, productList, new Delivery(address,payment, total));
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public List<Order> listOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }


}
