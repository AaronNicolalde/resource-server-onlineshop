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
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer c) {
        if(customerRepository.findByUsername(c.getUsername()) != null){
            return null;
        }
        c.getAddress().add(new Address("address1"));
        c.getAddress().add(new Address("address2"));
        c.getPaymentMethod().add(new PaymentMethod("credit card"));
        c.getPaymentMethod().add(new PaymentMethod("debit card"));
        return customerRepository.save(c);
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
}
