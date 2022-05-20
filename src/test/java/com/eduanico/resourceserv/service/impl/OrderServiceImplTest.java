package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.service.OrderService;
import com.eduanico.resourceserv.web.model.Checkout;
import com.eduanico.resourceserv.web.model.Customer;
import com.eduanico.resourceserv.web.model.Order;
import com.eduanico.resourceserv.web.model.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Order service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CheckoutRepository checkoutRepository;

    private Checkout checkout;

    @BeforeEach
    void setup(){
    }

    @Test
    @DisplayName("Create order returns order when successful")
    void createOrder_ReturnOrder_WhenSuccessful() {
        Checkout c = createCheckout();
        checkoutRepository.save(c);
        Order order = orderService.createOrder(c.getCheckoutId());

        assertThat(order.getCustomerName()).isEqualTo("eduanico");

    }

    @Test
    @DisplayName("Create order returns null when failed")
    void createOrder_ReturnNull_WhenFail() {
        assertThat(orderService.createOrder(2L)).isEqualTo(null);
    }

    @Test
    @DisplayName("Create order returns order when successful")
    void listOrders() {
        assertThat(orderService.listOrders().size()).isEqualTo(1);
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