package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.CheckoutService;
import com.eduanico.resourceserv.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    CheckoutRepository checkoutRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Checkout> listCheckouts() {
        return checkoutRepository.findAll();
    }

    @Override
    public Checkout getCheckoutInformation(@Positive Long checkoutId) {
        if(checkoutRepository.findByCheckoutId(checkoutId) == null){
            return null;
        }
        return checkoutRepository.findByCheckoutId(checkoutId);
    }

    @Override
    public Checkout startCheckout(@NotEmpty String username,@NotEmpty String productName, @Positive double quantity) {
        Product product = productRepository.findByName(productName);
        Customer customer = customerRepository.findByUsername(username);
        if( customer != null && product != null){
            List<Product> productList = new ArrayList<>();
            product.setQuantity(quantity);
            productList.add(product);
            return checkoutRepository.save(new Checkout(username, productList, product.getPrice() * quantity));
        }
        return null;
    }

    @Override
    public void addProductToCheckout(@Positive Long checkoutId,@NotEmpty String productName, @Positive double quantity) {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        Product product = productRepository.findByName(productName);
        if(checkout!= null && product != null){
            if(checkout.getProductList().contains(product)){
                int index = checkout.getProductList().indexOf(product);
                double actualQuantity = checkout.getProductList().get(index).getQuantity();
                double totalQuantity = actualQuantity+quantity;
                checkout.getProductList().get(index).setQuantity(totalQuantity);
            }else{
                product.setQuantity(quantity);
                checkout.getProductList().add(product);
            }
            setTotalPrice(checkout);
        }
        System.err.println("Product or checkout not found");
    }

    @Override
    public void setTotalPrice(@Valid Checkout checkout){
        double total = 0;
        for(Product p : checkout.getProductList()){
            total+= p.getPrice()*p.getQuantity();
        }
        checkout.setTotal(total);
    }

    @Override
    public void modifyProductQuantity(@Positive Long checkoutId, @NotEmpty String productName, @Positive double quantity) {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        Product product = productRepository.findByName(productName);
        if(checkout!= null && product != null){
            if(checkout.getProductList().contains(product)){
                int index = checkout.getProductList().indexOf(product);
                checkout.getProductList().get(index).setQuantity(quantity);
                setTotalPrice(checkout);
            }
        }
        System.err.println("Product or checkout not found");
    }

    @Override
    public void removeProductFromCheckout(@Positive Long checkoutId, @NotEmpty String productName) {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        Product product = productRepository.findByName(productName);
        if(checkout!= null && product != null && checkout.getProductList().contains(product)){
            int index = checkout.getProductList().indexOf(product);
            checkout.getProductList().remove(index);
            if(checkout.getProductList().isEmpty()){
                checkoutRepository.delete(checkout);
            }
        }
        setTotalPrice(checkout);
    }

    @Override
    public void setAddressForDelivery(@Positive Long checkoutId,@Positive  int addressIndex) throws IndexOutOfBoundsException {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        if(checkout.getDeliveryAddress() == null){
            Customer customer = customerRepository.findByUsername(checkout.getUsername());
            if(customer != null){
                Address address = customer.getAddress().get(addressIndex);
                checkout.setDeliveryAddress(address);
            }
        }else{
            System.err.println("Address already set. Use modify instead");
        }
    }

    @Override
    public void modifyAddressForDelivery(@Positive Long checkoutId,@Positive int addressIndex) throws IndexOutOfBoundsException{
        Checkout checkout = checkoutRepository.getById(checkoutId);
        if(checkout.getDeliveryAddress() == null ){
            System.err.println("Address not set. Use set instead");
        }else{
            Customer customer = customerRepository.findByUsername(checkout.getUsername());
            if(customer != null) {
                Address address = customer.getAddress().get(addressIndex);
                checkout.setDeliveryAddress(address);
            }
        }
    }

    @Override
    public void setPaymentMethod(@Positive Long checkoutId,@Positive  int paymentMethodIndex) throws IndexOutOfBoundsException{
        Checkout checkout = checkoutRepository.getById(checkoutId);
        if(checkout.getPaymentMethodSelected() == null){
            Customer customer = customerRepository.findByUsername(checkout.getUsername());
            if(customer != null) {
                PaymentMethod paymentMethod = customer.getPaymentMethod().get(paymentMethodIndex);
                checkout.setPaymentMethodSelected(paymentMethod);
            }
        }else{
            System.err.println("Payment already set. Use modify instead");
        }
    }

    @Override
    public void modifyPaymentMethod(@Positive Long checkoutId,@Positive int paymentMethodIndex) {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        if(checkout.getPaymentMethodSelected() == null){
            System.err.println("Payment not set. Use set instead");
        }else{
            Customer customer = customerRepository.findByUsername(checkout.getUsername());
            if(customer != null) {
                PaymentMethod paymentMethod = customer.getPaymentMethod().get(paymentMethodIndex);
                checkout.setPaymentMethodSelected(paymentMethod);
            }
        }
    }



}


