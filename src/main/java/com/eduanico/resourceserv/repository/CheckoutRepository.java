package com.eduanico.resourceserv.repository;

import com.eduanico.resourceserv.web.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByCheckoutId(Long id);

}
