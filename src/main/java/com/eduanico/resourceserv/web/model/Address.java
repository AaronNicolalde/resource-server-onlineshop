package com.eduanico.resourceserv.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    private String country;

    private String address;

    private String zipCode;

    public Address(String country, String address, String zipCode) {
        this.country = country;
        this.address = address;
        this.zipCode = zipCode;
    }
}
