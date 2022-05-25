package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.service.impl.CustomerServiceImpl;
import com.eduanico.resourceserv.web.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.Principal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Customer controller Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerControllerTest {

    @Spy
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerServiceImpl customerService;

    @Mock
    private Principal principal;

    private String defaultName = "name";

    @Mock
    private ResponseEntity responseEntity;

    @Test
    @DisplayName("Add customer returns api response when successful")
    void addCustomer_WhenSuccessful() {
        when(principal.getName()).thenReturn(defaultName);
        Customer customer = new Customer(principal.getName());
        when(customerService.createCustomer(new Customer(anyString()))).thenReturn(customer);

        assertThat(customerController.addCustomer(principal)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Get customers returns api response when successful")
    void getCustomers_WhenSuccessful() {
        assertThat(customerController.getCustomers()).isInstanceOf(ResponseEntity.class);
    }
}