package com.eduanico.resourceserv.service;

import com.eduanico.resourceserv.web.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer c);
    List<Customer> listCustomers();

}
