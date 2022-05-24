package com.eduanico.resourceserv.web.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
//    @ManyToOne(targetEntity=Address.class, mappedBy="address", fetch=FetchType.EAGER)
//    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL )
    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=Address.class)
    private List<Address> address;
//

//    @Autowired
//    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL )
    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=PaymentMethod.class)
    private List<PaymentMethod> paymentMethod;

    public Customer(String username) {
        this.username = username;
        address = new ArrayList<>();
        paymentMethod = new ArrayList<>();
    }

    public Customer(Long customerId, String username, ArrayList<Address> address, ArrayList<PaymentMethod> paymentMethod) {
        this.customerId = customerId;
        this.username = username;
        this.address = new ArrayList<>();
        this.paymentMethod = new ArrayList<>();
    }
}
