package com.cinematickets.service;

import com.cinematickets.entity.Customer;
import com.cinematickets.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer save(Customer customer) {
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent((s) -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already in system");
                });
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is NOT in system"));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
