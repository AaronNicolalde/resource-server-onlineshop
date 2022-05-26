package com.eduanico.resourceserv.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@Table(name = "paymentMethod")
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PaymentMethod {

    private String paymentMethod;

    public PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
