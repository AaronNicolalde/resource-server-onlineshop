package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.common.ApiResponse;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
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

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> body = productService.listProducts();
        return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product added"),HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") final Long id, @RequestBody @Valid Product product){
        productService.updateExistingProduct(id, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product updated"),HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") final Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product deleted"),HttpStatus.OK);
    }
}
