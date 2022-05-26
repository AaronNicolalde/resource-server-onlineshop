package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Order controller Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderControllerTest {

    @Autowired
    OrderController orderController;

    @Mock
    OrderService orderService;


    @Test
    @DisplayName("Add customer returns api response when successful")
    void addCustomer_WhenSuccessful() {
        assertThat(orderController.addOrder(1L)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Add customer returns api response when successful")
    void addCustomer_WhenOrderNull() {
        when(orderService.createOrder(anyLong())).thenReturn(null);

        assertThat(orderController.addOrder(1L)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Get products returns api response when successful")
    void getProducts_WhenSuccessful() {
        assertThat(orderController.getProducts()).isInstanceOf(ResponseEntity.class);
    }
}