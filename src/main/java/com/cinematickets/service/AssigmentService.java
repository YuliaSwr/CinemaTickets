package com.cinematickets.service;

import com.cinematickets.entity.Assigment;
import com.cinematickets.repository.AssigmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssigmentService {

    @Autowired
    private AssigmentRepository assigmentRepository;

    public Assigment save(Assigment assigment) {
        if (assigmentRepository.findByTicketId(assigment.getTicketId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assigment for this ticket is already in system");
        }
        return assigmentRepository.save(assigment);
    }

    public Assigment getByTicketId(Long ticketId) {
         assigmentRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assigment for this ticket is NOT in system"));
        return assigmentRepository.findByTicketId(ticketId).get();
    }

    public List<Assigment> getAll() {
        return assigmentRepository.findAll();
    }

    public Assigment update(Assigment newAssigment) {
        Assigment assigment = assigmentRepository.findByTicketId(newAssigment.getTicketId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assigment for this ticket is NOT in system"));
        assigment.setOperator(newAssigment.getOperator());
        assigment.setStatus(newAssigment.getStatus());
        return assigmentRepository.save( assigment);
    }

    public void delete(Long ticketId) {
        assigmentRepository.deleteByTicketId(ticketId);
    }
}
