package com.eduanico.resourceserv.web.controller;


import com.eduanico.resourceserv.common.ApiResponseCustom;
import com.eduanico.resourceserv.service.CustomerService;
import com.eduanico.resourceserv.web.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "To create a customer in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "201", description = "Customer created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "500", description = "Server error", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponseCustom> addCustomer(Principal principal){
        String username = principal.getName();

        if(customerService.createCustomer(new Customer(username)) != null){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Customer created"), HttpStatus.CREATED);
        }
        return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Customer already created"), HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "To fetch all customers in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Fetch all customers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> body = customerService.listCustomers();
        return new ResponseEntity<List<Customer>>(body, HttpStatus.OK);
    }

}
