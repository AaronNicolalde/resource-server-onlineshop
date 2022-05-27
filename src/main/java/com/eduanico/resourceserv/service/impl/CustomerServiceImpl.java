package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.service.CustomerService;
import com.eduanico.resourceserv.web.model.Address;
import com.eduanico.resourceserv.web.model.Customer;
import com.eduanico.resourceserv.web.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer c) {
        if(customerRepository.findByUsername(c.getUsername()) != null){
            return null;
        }
        c.getAddress().add(new Address("USA", "boulevard av", "91905"));
        c.getAddress().add(new Address("USA", "miami FL", "33101"));
        c.getPaymentMethod().add(new PaymentMethod("credit card","Eduardo Nicolalde", "1111222233334444","11/24","111"));
        c.getPaymentMethod().add(new PaymentMethod("debit card","Eduardo Nicolalde", "444433332222111","04/26","223"));
        return customerRepository.save(c);
    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }
}
