package com.sd.SpringRESTApi.service;

import com.sd.SpringRESTApi.entity.Customer;
import java.util.List;


public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int customerId);
    Customer save(Customer customer);
    void deleteById(int customerId);
}
