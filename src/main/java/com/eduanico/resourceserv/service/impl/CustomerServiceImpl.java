package com.eduanico.resourceserv.service.impl;

import com.eduanico.resourceserv.repository.CustomerRepository;
import com.eduanico.resourceserv.service.CustomerService;
import com.eduanico.resourceserv.web.model.Customer;
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
        return customerRepository.save(c);
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
}
