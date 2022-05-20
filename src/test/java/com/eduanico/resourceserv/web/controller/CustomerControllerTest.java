package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.web.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Customer controller Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Test
    @DisplayName("Add customer returns api response when successful")
    void addCustomer_WhenSuccessful() {
        assertThat(customerController.addCustomer(new Customer("eduanico"))).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Get customers returns api response when successful")
    void getCustomers_WhenSuccessful() {
        assertThat(customerController.getCustomers()).isInstanceOf(ResponseEntity.class);
    }
}