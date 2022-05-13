package com.cinematickets.service;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.Ticket;
import com.cinematickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AssignmentService assignmentService;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket) {
        ticketRepository.save(ticket);
        assignmentService.save(ticket);
        return ticket;
    }

    public int getAmountOfMovie(String movie) {
        return ticketRepository.findAllByMovieIgnoreCase(movie).size();
    }

    public void deleteById(Long id) {
        Assignment assignment = assignmentService.getByTicketId(id);
        assignmentService.deleteById(assignment.getId());
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getAllByCustomer(String email) {
        return ticketRepository.findAllByCustomerEmail(email);
    }
}
