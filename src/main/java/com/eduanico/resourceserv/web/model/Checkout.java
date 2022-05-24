package com.eduanico.resourceserv.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="checkout")
@Getter @Setter
@NoArgsConstructor
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false , updatable = false)
    private Long checkoutId;

    private String username;

    @Autowired
    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL )
    private List<Product> productList = new ArrayList<>();

    @PositiveOrZero
    private double total;

    @Embedded
    private Address deliveryAddress;

    @Embedded
    private PaymentMethod paymentMethodSelected;


    public Checkout(String username, List<Product> productList, double total) {
        this.username = username;
        this.productList = productList;
        this.total = total;
    }

    public Checkout(Long checkoutId, String username, List<Product> productList, double total, Address deliveryAddress, PaymentMethod paymentMethodSelected) {
        this.checkoutId = checkoutId;
        this.username = username;
        this.productList = productList;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethodSelected = paymentMethodSelected;
    }
}
