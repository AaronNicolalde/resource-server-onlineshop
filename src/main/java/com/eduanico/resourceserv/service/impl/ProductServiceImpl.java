package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public void addProduct(Product p) {
        productRepository.save(p);
    }

    @Override
    public Product updateExistingProduct(Long id, Product p) {
        Product product = productRepository.getById(id);
        product.setPrice(p.getPrice());
        product.setQuantity(p.getQuantity());
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresent(product -> productRepository.delete(product));
    }


//    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
//        Product product = getProductFromDto(productDto, category);
//        product.setId(productID);
//        productRepository.save(product);
//    }
//
//
//    public Product getProductById(Integer productId) throws ProductNotExistException {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//        if (!optionalProduct.isPresent())
//            throw new ProductNotExistException("Product id is invalid " + productId);
//        return optionalProduct.get();
//    }


}
