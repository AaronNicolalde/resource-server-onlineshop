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
    private Long checkoutId;

    @Autowired
    @OneToOne
    private Customer customer;

    @Autowired
    @OneToMany(fetch=FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();

    @PositiveOrZero
    private double total;

    private ArrayList<String> address;

    private ArrayList<String> paymentMethod;


    public Checkout(Customer customer, List<Product> productList, double total) {
        this.customer = customer;
        this.productList = productList;
        this.total = total;
        this.address = new ArrayList<>();
        this.paymentMethod = new ArrayList<>();
    }
}
