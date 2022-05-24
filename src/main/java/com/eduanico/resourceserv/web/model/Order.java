package com.eduanico.resourceserv.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Positive
    private Long checkoutId;

    @NotNull
    private String customerName;

    @Autowired
    @OneToMany(fetch=FetchType.EAGER)
    private List<Product> productList;

    @Autowired
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Delivery delivery;


    public Order(Long checkoutId, String customerName, List<Product> productList, Delivery delivery) {
        this.checkoutId = checkoutId;
        this.customerName = customerName;
        this.productList = productList;
        this.delivery = delivery;
    }

}
