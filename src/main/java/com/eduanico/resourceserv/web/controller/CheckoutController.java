package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.common.ApiResponseCustom;
import com.eduanico.resourceserv.service.CheckoutService;
import com.eduanico.resourceserv.web.model.Checkout;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/checkouts")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Operation(summary = "Add a checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "201", description = "Checkout created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping("/add-checkout")
    public ResponseEntity<ApiResponseCustom> addCheckout(Principal principal, @RequestParam("productName") String productName, @RequestParam("quantity") double quantity){
        try{
            checkoutService.startCheckout(principal.getName(), productName, quantity);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Checkout created"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Error creating checkout"), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Fetch a checkout",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getCheckoutInformation(@PathVariable("id") Long checkoutId) {
        Checkout body = checkoutService.getCheckoutInformation(checkoutId);
        return new ResponseEntity<Checkout> (body, HttpStatus.OK);
    }


    @Operation(summary = "Fetch checkouts in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Fetch all checkouts",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Checkout>> getCheckouts() {
        try{
            List<Checkout> body = checkoutService.listCheckouts();
            return new ResponseEntity<List<Checkout>>(body, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Add a product to a checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "201", description = "Product added to checkout",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping("/add-product")
    public ResponseEntity<ApiResponseCustom> addProductToCheckout(@RequestParam("checkoutId") Long checkoutId, @RequestParam("productName") String productName, @RequestParam("quantity") double quantity){
        try{
            checkoutService.addProductToCheckout(checkoutId, productName, quantity);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product added to checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Product failed to add to checkout"), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Update product quantity in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Product quantity updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PutMapping("/modify-quantity")
    public ResponseEntity<ApiResponseCustom> modifyQuantityOfProduct(@RequestParam("checkoutId") Long checkoutId, @RequestParam("productName") String productName, @RequestParam("quantity") double quantity){
        try{
            checkoutService.modifiyProductQuantity(checkoutId, productName, quantity);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product quantity updated on checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Product quantity not updated on checkout"), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete a product in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Product deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @DeleteMapping(value = "/delete-product")
    public ResponseEntity<ApiResponseCustom> deleteProductFromCheckout(@RequestParam("checkoutId") Long checkoutId, @RequestParam("productName") String productName){
        try{
            checkoutService.removeProductFromCheckout(checkoutId, productName);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product removed from checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Product not removed from checkout"), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Set customer address in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Customer address set",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping("/set-address")
    public ResponseEntity<ApiResponseCustom> setAddressForCustomer(@RequestParam("checkoutId") Long checkoutId, @RequestParam("index") int index){
        try{
            checkoutService.setAddressForDelivery(checkoutId, index);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Address set for customer from checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Address not set for customer from checkout"), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Update customer address in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Customer address updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PutMapping("/modify-address")
    public ResponseEntity<ApiResponseCustom> modifyAddressForCustomer(@RequestParam("checkoutId") Long checkoutId, @RequestParam("index") int index){
        try{
            checkoutService.modifyAddressForDelivery(checkoutId, index);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Address modify for customer from checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Address not modify for customer from checkout"), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Set customer payment method in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Customer payment method set",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping("/set-payment")
    public ResponseEntity<ApiResponseCustom> setPaymentForCustomer(@RequestParam("checkoutId") Long checkoutId, @RequestParam("index") int index){
        try{
            checkoutService.setPaymentMethod(checkoutId, index);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Payment method set for customer from checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Payment method fail to set for customer from checkout"), HttpStatus.BAD_REQUEST);

        }

    }

    @Operation(summary = "Update customer payment method in checkout in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Customer payment method updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PutMapping("/modify-payment")
    public ResponseEntity<ApiResponseCustom> modifyPaymentForCustomer(@RequestParam("checkoutId") Long checkoutId, @RequestParam("index") int index){
        try{
            checkoutService.modifyPaymentMethod(checkoutId, index);
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Payment method momdify for customer from checkout"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(false,"Error in modify Payment method for customer from checkout"), HttpStatus.BAD_REQUEST);
        }
    }


}
