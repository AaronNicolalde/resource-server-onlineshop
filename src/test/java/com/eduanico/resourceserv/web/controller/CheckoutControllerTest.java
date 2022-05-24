package com.eduanico.resourceserv.web.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Checkout controller Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CheckoutControllerTest {

    @Autowired
    CheckoutController checkoutController;

    @Test
    @DisplayName("Add checkout returns api response when successful")
    void addCheckout_ReturnsApiResponde_WhenSuccessful(Principal principal) {
        assertThat(checkoutController.addCheckout(principal,"apple",10.0)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Add checkout returns api response when exception raised")
    void addCheckout_ReturnsApiResponde_WhenException() throws Exception{
        CheckoutController mock = mock(CheckoutController.class);
        Exception e = new Exception();

        doThrow(EntityNotFoundException.class)
                .when(mock)
                .addCheckout(new Principal() {
                    @Override
                    public String getName() {
                        return "eduanico@test.com";
                    }
                }, "apple", 10.0);
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "eduanico@test.com";
            }
        };
        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(()->mock.addCheckout(principal,"apple",10.0));

    }



    @Test
    @DisplayName("Get checkout informatiton retursn api response when successful")
    void getCheckoutInformation_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.getCheckoutInformation(1L)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Get checkouts returns api responses when successful")
    void getCheckouts_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.getCheckouts()).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Add product to checkout returns api response when successful")
    void addProductToCheckout_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.addProductToCheckout(1L,"apple",10.0)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Modify quantity of product returns api response when successful")
    void modifyQuantityOfProduct_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.modifyQuantityOfProduct(1L,"apple",2.0)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Delete product from checkout returns api response when successful")
    void deleteProductFromCheckout_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.deleteProductFromCheckout(1L,"apple")).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Set address for customer returns api response when successful")
    void setAddressForCustomer_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.setAddressForCustomer(1L,0)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Modify address for customer returns api response when successful")
    void modifyAddressForCustomer_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.modifyAddressForCustomer(1L,1)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Set payment for customer returns api responde when successful")
    void setPaymentForCustomer_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.setPaymentForCustomer(1L,0)).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Modify payment method for customer returns api response when successful")
    void modifyPaymentForCustomer_ReturnsApiResponde_WhenSuccessful() {
        assertThat(checkoutController.modifyPaymentForCustomer(1L,1)).isInstanceOf(ResponseEntity.class);
    }


}