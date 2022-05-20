package com.eduanico.resourceserv.web.controller;


import com.eduanico.resourceserv.common.ApiResponse;
import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.service.CustomerService;
import com.eduanico.resourceserv.web.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody @Valid Customer c){
        customerService.createCustomer(c);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Customer created"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> body = customerService.listCustomers();
        return new ResponseEntity<List<Customer>>(body, HttpStatus.OK);
    }

}
