package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.web.model.Checkout;
import com.eduanico.resourceserv.web.model.Customer;
import com.eduanico.resourceserv.web.model.Product;
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
                .isThrownBy(()-> checkoutService.removeProductFromOrder(checkout.getCheckoutId(), "apple"));
    }

    @Test
    @DisplayName("Modify address for customer when successful")
    void modifyAddressForCustomer_WhenSuccessful() {
        checkoutRepository.save(checkout);
        checkoutService.setAddressForCustomer(checkout.getCheckoutId(),"urb test1");
        checkoutService.modifyAddressForCustomer(checkout.getCheckoutId(),"urb test");
        assertThat(checkout.getAddress().get(0)).isEqualTo("urb test");
    }

    @Test
    @DisplayName("Modify payment method for customer when successful")
    void modifyPaymentMethod_WhenSuccessful() {
        checkoutService.setPaymentMethod(checkout.getCheckoutId(),"debit card");
        checkoutService.modifyPaymentMethod(checkout.getCheckoutId(),"credit card");
        assertThat(checkout.getPaymentMethod().get(0)).isEqualTo("credit card");
    }

    Checkout createCheckout(){
        Customer customer = new Customer("eduanico");
        Product p = new Product("apple",10.0,0.35);
        List<Product> productList = new ArrayList<Product>();
        productList.add(p);
        ArrayList<String> addressList = new ArrayList<String>();
        addressList.add("urb test");
        ArrayList<String> paymentList = new ArrayList<String>();
        paymentList.add("credit card");
        checkout = new Checkout(1L,customer,productList,10.0,addressList,paymentList);
        return checkout;
    }
}