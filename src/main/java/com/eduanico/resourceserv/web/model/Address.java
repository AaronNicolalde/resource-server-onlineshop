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

    private String address;



    public Address(String address) {
        this.address = address;
    }
}
