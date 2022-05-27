package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.web.model.*;
import org.assertj.core.api.Assertions;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Checkout service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CheckoutServiceImplTest {

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    private Checkout checkout;

    private Customer customer;

    private Product product;

    @BeforeEach
    void setup(){
        checkout = createCheckout();
    }

    @Test
    @DisplayName("List checkouts method returns list when successful")
    void listCheckouts_ReturnsList_WhenSuccessful() {
        when(checkoutRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(checkoutService.listCheckouts()).isInstanceOf(ArrayList.class);
    }

    @Test
    @DisplayName("Get checkout information when successful")
    void getCheckoutInformation_ReturnsInformation_WhenSuccessful() {
        when(checkoutRepository.findByCheckoutId(anyLong())).thenReturn(checkout);

        assertThat(checkoutService.getCheckoutInformation(1L).getUsername()).isEqualTo("eduanico@test.com");
    }


    @Test
    @DisplayName("Get checkout information returns null when fail")
    void getCheckoutInformation_ReturnsNull_WhenFailed() {
        when(checkoutRepository.findByCheckoutId(anyLong())).thenReturn(null);

        assertThat(checkoutService.getCheckoutInformation(2L)).isNull();
    }

    @Test
    @DisplayName("Start checkout returns checkout when successful")
    void startCheckout_ReturnsCheckout_WhenSuccessful() {
        when(productRepository.findByName(anyString())).thenReturn(product);
        when(customerRepository.findByUsername(anyString())).thenReturn(customer);
        when(checkoutRepository.save(any(Checkout.class))).thenReturn(checkout);

        assertThat(checkoutService.startCheckout("eduanico","apple",10.0))
                .isInstanceOf(Checkout.class);
    }

    @Test
    @DisplayName("Start checkout returns null when failed")
    void startCheckout_ReturnsNull_WhenFailed() {
        when(productRepository.findByName(anyString())).thenReturn(null);
        when(customerRepository.findByUsername(anyString())).thenReturn(null);

        assertThat(checkoutService.startCheckout("eduanico","apple",10.0))
                .isNull();
    }

    @Test
    @DisplayName("Add product to checkout when checkout is valid")
    void addProductToCheckout_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(productRepository.findByName(anyString())).thenReturn(new Product());

        checkoutService.addProductToCheckout(1L,"apple",1.0);

        verify(checkoutRepository, times(1)).getById(1L);
    }

    @Test
    @DisplayName("Add product to checkout when checkout product already in product list")
    void addProductToCheckout_WhenProductIsAlready() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(productRepository.findByName(anyString())).thenReturn(checkout.getProductList().get(0));

        checkoutService.addProductToCheckout(1L,"apple",10.0);

        assertThat(checkout.getProductList().get(0).getQuantity()).isEqualTo(20.0);
    }

    @Test
    @DisplayName("Add product to checkout when checkout product not in product list")
    void addProductToCheckout_WhenProductNotExists() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(productRepository.findByName(anyString())).thenReturn(new Product());

        checkoutService.addProductToCheckout(1L,"bread",10.0);

        assertThat(checkout.getProductList().get(1).getQuantity()).isEqualTo(10.0);
    }



    @Test
    @DisplayName("Set total price to a checkout.")
    void setTotalPrice_WhenSuccessful() {
        checkoutService.setTotalPrice(checkout);

        assertThat(checkout.getTotal()).isPositive();
    }

    @Test
    @DisplayName("Modify a product quantity of a checkout.")
    void modifyProductQuantity_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(productRepository.findByName(anyString())).thenReturn(checkout.getProductList().get(0));
        when(customerRepository.findByUsername(anyString())).thenReturn(new Customer());

        checkoutService.modifyProductQuantity(1L,"apple",1.0);

        assertThat(checkout.getProductList().get(0).getQuantity()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("remove product from order delete checkout when list is empty")
    void removeProductFromOrder_DeleteCheckout_WhenEmpty() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(productRepository.findByName(anyString())).thenReturn(checkout.getProductList().get(0));

        checkoutService.removeProductFromCheckout(1L,"apple");

        assertThat(checkout.getProductList().size()).isZero();
    }

    @Test
    @DisplayName("Throw NullPointerException when product list is empty")
    void removeProductFromOrder_ThrowNullPointerException_WhenEmpty() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(()-> checkoutService.removeProductFromCheckout(checkout.getCheckoutId(), "apple"));
    }

    @Test
    @DisplayName("Set address for delivery when successful")
    void setAddressForDelivery_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(new Checkout());
        when(customerRepository.findByUsername(anyString())).thenReturn(customer);

        checkoutService.setAddressForDelivery(1L, 0);

        assertThat(checkout.getDeliveryAddress().getAddress()).isEqualTo("address1");
    }

    @Test
    @DisplayName("Set address for delivery when fail")
    void setAddressForDelivery_WhenFail() {
        when(checkoutRepository.getById(anyLong())).thenReturn(new Checkout());
        when(customerRepository.findByUsername(anyString())).thenReturn(null);

        checkoutService.setAddressForDelivery(1L, 0);

        assertThat(checkout.getDeliveryAddress().getAddress()).isEqualTo("address1");
    }



    @Test
    @DisplayName("Modify address for delivery when successful")
    void modifyAddressForDelivery_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);

        checkoutService.modifyAddressForDelivery(1L,0);

        assertThat(checkout.getDeliveryAddress().getAddress()).isEqualTo("address1");
    }

    @Test
    @DisplayName("Set Payment Method for delivery when successful")
    void setPaymentMethod_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(new Checkout());
        when(customerRepository.findByUsername(anyString())).thenReturn(customer);

        checkoutService.setPaymentMethod(1L, 0);

        assertThat(checkout.getPaymentMethodSelected().getPaymentMethod()).isEqualTo("credit card");
    }

    @Test
    @DisplayName("Modify payment method for delivery when successful")
    void modifyPaymentMethod_WhenSuccessful() {
        when(checkoutRepository.getById(anyLong())).thenReturn(checkout);
        when(customerRepository.findByUsername(anyString())).thenReturn(customer);

        checkoutService.modifyPaymentMethod(checkout.getCheckoutId(),1);

        assertThat(checkout.getPaymentMethodSelected().getPaymentMethod()).isEqualTo("debit card");
    }

    Checkout createCheckout(){
        customer = new Customer("eduanico@test.com");
        customer.getAddress().add(new Address("USA", "boulevard av", "91905"));
        customer.getAddress().add(new Address("USA", "miami FL", "33101"));
        customer.getPaymentMethod().add(new PaymentMethod("credit card","Eduardo Nicolalde", "1111222233334444","11/24","111"));
        customer.getPaymentMethod().add(new PaymentMethod("debit card","Eduardo Nicolalde", "444433332222111","04/26","223"));
        product = new Product("apple",10.0,0.35);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        checkout = new Checkout(1L,"eduanico@test.com",productList,10.0,new Address("USA", "address1", "91905"),new PaymentMethod("credit card","Eduardo Nicolalde", "1111222233334444","11/24","111"));
        return checkout;
    }
}