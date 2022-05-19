package com.eduanico.resourceserv.repository;

import com.eduanico.resourceserv.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Product, Long> {

}
