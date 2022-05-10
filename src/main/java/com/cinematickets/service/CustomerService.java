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
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already in system");
        }
        return customerRepository.save(customer);
    }

    public Customer getByEmail(String email) {
        customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is NOT in system"));
        return customerRepository.findByEmail(email).get();
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer update(Customer newCustomer) {
        Customer customer = customerRepository.findByEmail(newCustomer.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is NOT in system"));
        customer.setPassword(newCustomer.getPassword());
        customer.setTickets(newCustomer.getTickets());
        return customerRepository.save(customer);
    }

    public void delete(String email) {
        customerRepository.deleteByEmail(email);
    }
}
