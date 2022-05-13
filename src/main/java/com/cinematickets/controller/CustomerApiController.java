package com.cinematickets.controller;

import com.cinematickets.entity.Customer;
import com.cinematickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping()
    public Customer register(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
