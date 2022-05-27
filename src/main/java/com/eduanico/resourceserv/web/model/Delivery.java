package com.eduanico.resourceserv.web.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Embedded
    private Address deliveryAddress;

    @Embedded
    private PaymentMethod registerPayment;

    @PositiveOrZero
    private double totalPurchase;

    public Delivery(Address deliveryAddress, PaymentMethod registerPayment, double totalPurchase) {
        this.deliveryAddress = deliveryAddress;
        this.registerPayment = registerPayment;
        this.totalPurchase = totalPurchase;
    }
}
