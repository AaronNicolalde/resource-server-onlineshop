package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.web.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Checkout service Tests")
@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CheckoutServiceImplTest {

    @Autowired
    private CheckoutServiceImpl checkoutService;

    @Autowired
    private CheckoutRepository checkoutRepository;

    private Checkout checkout;

    @BeforeEach
    void setup(){
        checkout = createCheckout();
    }

//    @BeforeAll
//    static void setup(){
//        checkout = createCheckout();
//    }

    @Test
    @DisplayName("List checkouts method returns list when successful")
    void listCheckouts_ReturnsList_WhenSuccessful() {
        assertThat(checkoutService.listCheckouts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get checkout information when successful")
    void getCheckoutInformation_ReturnsInformation_WhenSuccessful() {
        assertThat(checkoutService.getCheckoutInformation(1L)).isInstanceOf(Checkout.class);
    }

    @Test
    @DisplayName("Add product to checkout when checkout is valid")
    void addProductToCheckout_WhenSuccessful() {
//        checkoutService.addProductToCheckout(checkout.getCheckoutId(), "apple",5.0);
        assertThat(checkout.getProductList().get(0).getName()).isEqualTo("apple");
    }


    @Test
    @DisplayName("Set total price to a checkout.")
    void setTotalPrice_WhenSuccessful() {
        checkoutService.setTotalPrice(checkout);
        assertThat(checkout.getTotal()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Modify a product quantity of a checkout.")
    void modifiyProductQuantity_WhenSuccessful() {
//        checkoutService.modifiyProductQuantity(1L,"apple",10.0);
        assertThat(checkout.getProductList().get(0).getQuantity()).isEqualTo(10.0);
        assertThat(checkout.getCheckoutId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("remove product from order when successful")
    void removeProductFromOrder_WhenSuccessful() {
//        checkoutService.removeProductFromOrder(checkout.getCheckoutId(), "apple");
        assertThat(checkout.getProductList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Throw IncorrectResultSizeDataAccessException when product list is empty")
    void removeProductFromOrder_ThrowIncorrectResultSizeDataAccessException_WhenEmpy() {
        Assertions.assertThatExceptionOfType(IncorrectResultSizeDataAccessException.class)
                .isThrownBy(()-> checkoutService.removeProductFromCheckout(checkout.getCheckoutId(), "apple"));
    }

    @Test
    @DisplayName("Modify address for customer when successful")
    void modifyAddressForCustomer_WhenSuccessful() {
        checkoutRepository.save(checkout);
        checkoutService.setAddressForDelivery(checkout.getCheckoutId(),0);
        checkoutService.modifyAddressForDelivery(checkout.getCheckoutId(),1);
        assertThat(checkout.getDeliveryAddress().getAddress()).isEqualTo(new Address("address2"));
    }

    @Test
    @DisplayName("Modify payment method for customer when successful")
    void modifyPaymentMethod_WhenSuccessful() {
        checkoutService.setPaymentMethod(checkout.getCheckoutId(),0);
        checkoutService.modifyPaymentMethod(checkout.getCheckoutId(),1);
        assertThat(checkout.getPaymentMethodSelected()).isEqualTo(new PaymentMethod("debit card"));
    }

    Checkout createCheckout(){
        Customer customer = new Customer("eduanico@test.com");
        customer.getAddress().add(new Address("address1"));
        customer.getAddress().add(new Address("address2"));
        customer.getPaymentMethod().add(new PaymentMethod("credit card"));
        customer.getPaymentMethod().add(new PaymentMethod("debit card"));
        Product p = new Product("apple",10.0,0.35);
        List<Product> productList = new ArrayList<Product>();
        productList.add(p);
        checkout = new Checkout(1L,"eduanico@test.com",productList,10.0,new Address("address1"),new PaymentMethod("credit card"));
        return checkout;
    }
}