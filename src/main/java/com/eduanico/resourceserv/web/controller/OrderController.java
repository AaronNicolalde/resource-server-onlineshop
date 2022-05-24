package com.eduanico.resourceserv.web.controller;


import com.eduanico.resourceserv.common.ApiResponseCustom;
import com.eduanico.resourceserv.service.OrderService;
import com.eduanico.resourceserv.web.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "To save an order in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "201", description = "Order created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "500", description = "Server error", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponseCustom> addOrder(@RequestParam("checkoutId") Long checkoutId){
        orderService.createOrder(checkoutId);
        return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Order created"), HttpStatus.CREATED);
    }

    @Operation(summary = "To fetch all orders in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Fetch all orders",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Order>> getProducts() {
        List<Order> body = orderService.listOrders();
        return new ResponseEntity<List<Order>>(body, HttpStatus.OK);
    }

}
