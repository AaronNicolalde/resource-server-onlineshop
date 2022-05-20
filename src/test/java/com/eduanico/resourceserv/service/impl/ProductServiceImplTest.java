package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Product service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductRepository productRepository;

    private
    Product product;

    @BeforeEach
    void setup(){
        product = createProduct();
    }

    @Test
    @DisplayName("Returns list of products when successful")
    void listProducts_ReturnsList_WhenSuccesful() {
        ProductService mock = mock(ProductService.class);
        List<Product> products = productService.listProducts();
        when(mock.listProducts()).thenReturn(products);
        assertThat(products).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("Add product method save product when successful")
    void addProduct_SaveProduct_WhenSuccessful() {
        productService.addProduct(product);
        productRepository.deleteById(product.getProductId());
    }

    @Test
    @DisplayName("Update existing product return updated product when successful")
    void updateExistingProduct_UpdateProduct_WhenSuccessful() {
        Product updateProduct = createProduct();
        updateProduct.setQuantity(14);
        productRepository.save(product);
        productService.addProduct(product);

        Product newProduct = productService.updateExistingProduct(1L, updateProduct);

        assertThat(newProduct.getQuantity()).isEqualTo(14);
    }

    @Test
    @DisplayName("Update existing product return EntityNotFoundException when fail")
    void updateExistingProduct_ReturnEntityNotFoundException_WhenFail() {
        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(()-> productService.updateExistingProduct(5L, product));
    }

    @Test
    @DisplayName("Delete product when successful")
    void deleteProduct_WhenSuccessful() {
        productService.addProduct(product);
        productService.deleteProduct(2L);
        assertThat(productRepository.count()).isEqualTo(3);
    }

    Product createProduct(){
        return new Product("apple",10.0,0.30);
    }
}