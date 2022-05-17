package com.eduanico.resourceserv.web.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private double quantity;

    @NotNull
    @Positive
    private double price;

    public Product(String name, double quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


}
