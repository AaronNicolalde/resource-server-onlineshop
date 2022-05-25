package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.web.model.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Customer service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setup(){
        customer = new Customer("eduanico");
    }

    @Test
    @DisplayName("Create a customer return null when customer already exists")
    void createCustomer_ReturnNull_WhenCustomerExists(){
        when(customerRepository.findByUsername(any(String.class))).thenReturn(customer);

        assertThat(customerService.createCustomer(customer)).isEqualTo(null);
    }

    @Test
    @DisplayName("Create a customer when successful")
    void createCustomer_ReturnCustomer_WhenSuccessful(){
        when(customerRepository.findByUsername(any(String.class))).thenReturn(null);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        assertThat(customerService.createCustomer(customer)).isEqualTo(customer);
    }

    @Test
    @DisplayName("List all customers when successful")
    void listCustomers_ReturnListOfCustomers_WhenSuccessful(){
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(customerService.listCustomers()).isInstanceOf(ArrayList.class);
    }





}