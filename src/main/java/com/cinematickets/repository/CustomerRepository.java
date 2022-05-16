package com.cinematickets.repository;

import com.cinematickets.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();

    Optional<Customer> findByEmailIgnoreCase(String email);
}

