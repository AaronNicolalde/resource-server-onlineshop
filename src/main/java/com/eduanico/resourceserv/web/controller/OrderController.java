package com.eduanico.resourceserv.web.controller;


import com.eduanico.resourceserv.common.ApiResponse;
import com.eduanico.resourceserv.service.OrderService;
import com.eduanico.resourceserv.web.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCustomer(@RequestParam("checkoutId") Long checkoutId){
        orderService.createOrder(checkoutId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Order created"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getProducts() {
        List<Order> body = orderService.listOrders();
        return new ResponseEntity<List<Order>>(body, HttpStatus.OK);
    }

}
