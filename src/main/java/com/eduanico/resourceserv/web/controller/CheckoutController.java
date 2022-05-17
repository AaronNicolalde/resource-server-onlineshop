package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.common.ApiResponse;
import com.eduanico.resourceserv.service.CheckoutService;
import com.eduanico.resourceserv.web.model.Checkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkouts")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/add-checkout")
    public ResponseEntity<ApiResponse> addCheckout(@RequestParam("customerId") Long customerId,@RequestParam("productName") String productName,@RequestParam("quantity") double quantity){
        checkoutService.startCheckout(customerId, productName, quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Checkout created"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getCheckoutInformation(@PathVariable Long checkoutId) {
        Checkout body = checkoutService.getCheckoutInformation(checkoutId);
        return new ResponseEntity<Checkout>(body, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Checkout>> getCheckouts() {
        List<Checkout> body = checkoutService.listCheckouts();
        return new ResponseEntity<List<Checkout>>(body, HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse> addProductToCheckout(@RequestParam("checkoutId") Long checkoutId,@RequestParam("productName") String productName,@RequestParam("quantity") double quantity){
        checkoutService.addProductToCheckout(checkoutId, productName, quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product added to checkout"), HttpStatus.CREATED);
    }

    @PostMapping("/modify-quantity")
    public ResponseEntity<ApiResponse> modifyQuantityOfProduct(@RequestParam("checkoutId") Long checkoutId,@RequestParam("productName") String productName,@RequestParam("quantity") double quantity){
        checkoutService.modifiyProductQuantity(checkoutId, productName, quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product added to checkout"), HttpStatus.CREATED);
    }

    @PostMapping("/delete-product")
    public ResponseEntity<ApiResponse> deleteProductFromCheckout(@RequestParam("checkoutId") Long checkoutId,@RequestParam("productName") String productName){
        checkoutService.removeProductFromOrder(checkoutId, productName);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product removed from checkout"), HttpStatus.CREATED);
    }


}