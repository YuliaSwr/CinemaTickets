package com.cinematickets.repository;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.AssignmentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    Optional<Assignment> findByTicketId(Long id);

    List<Assignment> findAll();

    void deleteByTicketId(Long ticketId);

    List<Assignment> findAllByStatus(AssignmentStatus status);
}
