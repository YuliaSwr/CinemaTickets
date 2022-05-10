package com.cinematickets.repository;

import com.cinematickets.entity.Assigment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssigmentRepository extends CrudRepository<Assigment, Long> {
    Optional<Assigment> findByTicketId(Long id);

    List<Assigment> findAll();

    void deleteByTicketId(Long ticketId);
}
