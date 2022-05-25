package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.repository.OrderRepository;
import com.eduanico.resourceserv.web.model.Order;
import com.eduanico.resourceserv.web.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Order service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    CheckoutRepository checkoutRepository;

    private Checkout checkout;

    @BeforeEach
    void setup(){
        checkout = createCheckout();
    }

    @Test
    @DisplayName("Create order returns order when successful")
    void createOrder_ReturnOrder_WhenSuccessful() {
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(checkoutRepository.findByCheckoutId(any(Long.class))).thenReturn(checkout);

        assertThat(orderService.createOrder(checkout.getCheckoutId())).isInstanceOf(Order.class);
    }

    @Test
    @DisplayName("Create order returns null when failed")
    void createOrder_ReturnNull_WhenFail() {
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(checkoutRepository.findByCheckoutId(any(Long.class))).thenReturn(null);

        assertThat(orderService.createOrder(2L)).isEqualTo(null);
    }

    @Test
    @DisplayName("Create order returns order when successful")
    void listOrders() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<Order>());

        assertThat(orderService.listOrders()).isInstanceOf(ArrayList.class);
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