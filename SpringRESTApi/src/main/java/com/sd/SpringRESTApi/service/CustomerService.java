package com.sd.SpringRESTApi.service;

import com.sd.SpringRESTApi.dao.ICustomerDao;
import com.sd.SpringRESTApi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/*
This controller is only for Spring-Data-Jpa demo with Customer entity.

As ICustomerDao extends JpaRepository hence its DML methods are already
transactional, So no need to use @Transactional in service DML methods.
*/

@Service
public class CustomerService implements ICustomerService{
    private final ICustomerDao customerDao;

    @Autowired
    public CustomerService(ICustomerDao customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerDao.findAll();
    }

    @Override
    public Customer findById(int customerId) {
        Optional<Customer> data = this.customerDao.findById(customerId);
        Customer result = null;
        if(data.isPresent()){
            result = data.get();
        }
        return result;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerDao.save(customer);
    }

    @Override
    public void deleteById(int customerId) {
       this.customerDao.deleteById(customerId);
    }
}
