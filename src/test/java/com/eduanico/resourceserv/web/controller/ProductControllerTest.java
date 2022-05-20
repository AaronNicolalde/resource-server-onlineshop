package com.eduanico.resourceserv.web.controller;

import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.web.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Product Controller Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Get products returns api response when successful")
    void getProducts_WhenSuccessful() {
        assertThat(productController.getProducts()).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Add product returns api response when successful")
    void addProduct_WhenSuccessful() {
        assertThat(productController.addProduct(new Product("apple",10.0,0.35))).isInstanceOf(ResponseEntity.class);
    }

    @Test
    @DisplayName("Update product returns api response when successful")
    void updateProduct_WhenSuccessful() {
        Product p = productRepository.save(new Product("apple",11.0,0.50));
        assertThat(productController.updateProduct(p.getProductId(), new Product("apple",10.0,0.35))).isInstanceOf(ResponseEntity.class);

    }

    @Test
    @DisplayName("Delete product when successful")
    void delete_WhenSuccessful() {
        Product p = productRepository.save(new Product("apple",11.0,0.50));
        assertThat(productController.delete(p.getProductId())).isInstanceOf(ResponseEntity.class);
    }
}