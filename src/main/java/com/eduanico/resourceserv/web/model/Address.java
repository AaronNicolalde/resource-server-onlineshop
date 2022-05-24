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
//@Entity
public class Address {
//    @Id
//    @Column(name = "id", nullable = false)
//    private Long id;

    private String address;



    public Address(String address) {
        this.address = address;
    }
}
