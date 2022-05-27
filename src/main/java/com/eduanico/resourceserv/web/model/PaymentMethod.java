package com.eduanico.resourceserv.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@Table(name = "paymentMethod")
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class PaymentMethod {

    private String paymentMethod;

    private String cardName;

    private String cardNumber;

    private String expDate;

    private String cvv;

    public PaymentMethod(String paymentMethod, String cardName, String cardNumber, String expDate, String cvv) {
        this.paymentMethod = paymentMethod;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
    }
}
