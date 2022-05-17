package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProducts();
    void addProduct(Product p);
    Product updateExistingProduct(Long id,Product p);
    void deleteProduct(Long id);

}
