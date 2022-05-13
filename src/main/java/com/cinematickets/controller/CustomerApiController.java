package com.cinematickets.controller;

import com.cinematickets.entity.Customer;
import com.cinematickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/customer")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer register(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping()
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return "Customer successful deleted!";
    }
}
