package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.common.ApiResponseCustom;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @Operation(summary = "To fetch all products in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Fetch all products",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> body = productService.listProducts();
        return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
    }


    @Operation(summary = "Add a product in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "201", description = "Product created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PostMapping("/add")
    public ResponseEntity<ApiResponseCustom> addProduct(@RequestBody @Valid Product product){
        productService.addProduct(product);
        return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product added"),HttpStatus.CREATED);
    }


    @Operation(summary = "Update a product in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseCustom> updateProduct(@PathVariable("id") final Long id, @RequestBody @Valid Product product){
        productService.updateExistingProduct(id, product);
        return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product updated"),HttpStatus.OK);
    }


    @Operation(summary = "Delete a product in database")
    @ApiResponses(value ={
            @ApiResponse( responseCode = "200", description = "Product deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse ( responseCode = "404", description = "Not available", content = @Content),
            @ApiResponse ( responseCode = "401", description = "Not authorized", content = @Content)
    })
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<ApiResponseCustom> delete(@PathVariable("id") final Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<ApiResponseCustom>(new ApiResponseCustom(true,"Product deleted"),HttpStatus.OK);
    }
}
