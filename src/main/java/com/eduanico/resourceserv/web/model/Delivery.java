package com.eduanico.resourceserv.web.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    private String deliveryAddress;

    private String registerPayment;

    @PositiveOrZero
    private double totalPurchase;

    public Delivery(String address, String payment, double total) {
        this.deliveryAddress = address;
        this.registerPayment = payment;
        this.totalPurchase = total;
    }

}
