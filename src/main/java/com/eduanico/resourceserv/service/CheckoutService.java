package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Checkout;

import java.util.List;

public interface CheckoutService {

    Checkout startCheckout(String username, String productName, double quantity);
    void addProductToCheckout(Long checkoutId, String productName, double quantity);

    List<Checkout> listCheckouts();

    void modifiyProductQuantity(Long checkoutId, String productName, double quantity);
    void removeProductFromCheckout(Long checkoutId, String productName);

    void setAddressForDelivery(Long checkoutId, int index);
    void modifyAddressForDelivery(Long checkoutId, int index);

    void setPaymentMethod(Long checkoutId, int index);
    void modifyPaymentMethod(Long checkoutId, int index);

    void setTotalPrice(Checkout checkout);
    Checkout getCheckoutInformation(Long checkoutId);
}
