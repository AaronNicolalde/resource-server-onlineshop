package com.eduanico.resourceserv.web.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @NotNull
    private String deliveryAddress;

    @NotNull
    private String registerPayment;

    @PositiveOrZero
    private double totalPurchase;

}
