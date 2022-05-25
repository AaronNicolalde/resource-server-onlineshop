package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Product service Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
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

        assertThat(productService.listProducts()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("Add product method save product when successful")
    void addProduct_SaveProduct_WhenSuccessful() {
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        productService.addProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Update existing product return updated product when successful")
    void updateExistingProduct_UpdateProduct_WhenSuccessful() {
        Product updateProduct = createProduct();
        updateProduct.setQuantity(14);

        when(productRepository.getById(any(Long.class))).thenReturn(product);

        Product newProduct = productService.updateExistingProduct(1L, updateProduct);

        assertThat(newProduct.getQuantity()).isEqualTo(14);
    }

    @Test
    @DisplayName("Update existing product throws EntityNotFoundException when failed")
    void updateExistingProduct_ThrowException_WhenFailed() {
        Product updateProduct = createProduct();
        updateProduct.setQuantity(14);
        when(productRepository.getById(any(Long.class)))
                .thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                ()->{
                    productService.updateExistingProduct(2L, updateProduct);
                });
    }

    @Test
    @DisplayName("Delete product when successful")
    void deleteProduct_WhenSuccessful() {
        productService.deleteProduct(2L);

        assertThat(productRepository.findById(2L)).isInstanceOf(Optional.class).isEmpty();

    }

    Product createProduct(){
        return new Product("apple",10.0,0.30);
    }
}