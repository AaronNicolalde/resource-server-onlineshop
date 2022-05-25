package com.eduanico.resourceserv.web.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull
    private String username;

    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=Address.class)
    private List<Address> address;

    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=PaymentMethod.class)
    private List<PaymentMethod> paymentMethod;

    public Customer(String username) {
        this.username = username;
        address = new ArrayList<>();
        paymentMethod = new ArrayList<>();
    }

}
