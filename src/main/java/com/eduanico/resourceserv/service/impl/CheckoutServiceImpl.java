package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CheckoutRepository;
import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.CheckoutService;
import com.eduanico.resourceserv.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Checkout> listCheckouts() {
        return checkoutRepository.findAll();
    }

    @Override
    public Checkout getCheckoutInformation(Long checkoutId) {
        return checkoutRepository.findByCheckoutId(checkoutId);
    }

    @Override
    public Checkout startCheckout(String username, String productName, double quantity) {
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
    public void addProductToCheckout(Long checkoutId, String productName, double quantity) {
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
    public void setTotalPrice(Checkout checkout){
        double total = 0;
        for(Product p : checkout.getProductList()){
            total+= p.getPrice()*p.getQuantity();
        }
        checkout.setTotal(total);
    }

    @Override
    public void modifiyProductQuantity(Long checkoutId, String productName, double quantity) {
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
    public void removeProductFromCheckout(Long checkoutId, String productName) {
        Checkout checkout = checkoutRepository.getById(checkoutId);
        Product product = productRepository.findByName(productName);
        int index = checkout.getProductList().indexOf(product);
        checkout.getProductList().remove(index);
        if(checkout.getProductList().isEmpty()){
            checkoutRepository.delete(checkout);
        }
        setTotalPrice(checkout);
    }

    @Override
    public void setAddressForDelivery(Long checkoutId, int addressIndex) throws IndexOutOfBoundsException {
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
    public void modifyAddressForDelivery(Long checkoutId, int addressIndex) throws IndexOutOfBoundsException{
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
    public void setPaymentMethod(Long checkoutId, int paymentMethodIndex) throws IndexOutOfBoundsException{
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
    public void modifyPaymentMethod(Long checkoutId, int paymentMethodIndex) throws IndexOutOfBoundsException{
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


