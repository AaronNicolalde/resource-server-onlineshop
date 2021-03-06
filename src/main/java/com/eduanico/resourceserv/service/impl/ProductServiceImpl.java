package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.ProductRepository;
import com.eduanico.resourceserv.service.ProductService;
import com.eduanico.resourceserv.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product p) {
        if(productRepository.findByName(p.getName()) == null){
            productRepository.save(p);
        }
    }

    @Override
    public Product updateExistingProduct(Long id, Product p) throws EntityNotFoundException{
        Product product = productRepository.getById(id);
        if(product.getName().equals(p.getName())){
            product.setPrice(p.getPrice());
            product.setQuantity(p.getQuantity());
            return product;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresent(product -> productRepository.delete(product));
    }

}
