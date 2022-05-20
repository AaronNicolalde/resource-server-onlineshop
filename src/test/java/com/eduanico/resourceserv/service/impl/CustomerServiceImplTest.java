package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.service.CustomerService;
import com.eduanico.resourceserv.web.model.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Customer service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setup(){
        customer = new Customer("eduanico");
    }

    @Test
    @DisplayName("Create a customer when successful")
    void createCustomer_ReturnCustomer_WhenSuccessful(){
        Customer newCustomer = customerService.createCustomer(customer);
        assertThat(newCustomer.getUsername()).isEqualTo("eduanico");
    }

    @Test
    @DisplayName("List all customers when successful")
    void listCustomers_ReturnListOfCustomers_WhenSuccessful(){
        customerService.createCustomer(customer);
        List<Customer> newCustomer = customerService.listCustomers();
        assertThat(newCustomer.size()).isEqualTo(1);
    }





}