package com.sd.SpringRESTApi.controller;

import com.sd.SpringRESTApi.entity.Customer;
import com.sd.SpringRESTApi.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//This controller is only for Spring-Data-Jpa demo with Customer entity.

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAll(){
        return this.customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getById(@PathVariable int custmerId){
        Customer result = this.customerService.findById(custmerId);
        if(result==null){
            throw new RuntimeException("Employee not found with id: "+custmerId);
        }
        return result;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer newCustomer){
        //By setting customerId=0, we're forcing to insert instead of update.
        newCustomer.setId(0);
        return this.customerService.save(newCustomer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        Customer tempCustomer = this.customerService.findById(customerId);

        if(tempCustomer==null){
            throw new RuntimeException("Customer id not found- "+customerId);
        }

        this.customerService.deleteById(customerId);
        return "Customer with id: "+customerId+" deleted successfully";
    }
}
