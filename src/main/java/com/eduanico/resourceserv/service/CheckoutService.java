package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Checkout;

import java.util.List;
import java.util.Optional;

public interface CheckoutService {

    Checkout startCheckout(Long customerId, String productName, double quantity);
    void addProductToCheckout(Long checkoutId, String productName, double quantity);

    List<Checkout> listCheckouts();

    void modifiyProductQuantity(Long checkoutId, String productName, double quantity);
    void removeProductFromOrder(Long checkoutId, String productName);

    void setAddressForCustomer(Long checkoutId, String address);
    void modifyAddressForCustomer(Long checkoutId, String address);

    void setPaymentMethod(Long checkoutId, String paymentMethod);
    void modifyPaymentMethod(Long checkoutId, String paymentMethod);

    Optional<Checkout> getCheckoutInformation(Long checkoutId);

}
