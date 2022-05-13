package com.cinematickets.service;

import com.cinematickets.entity.Operator;
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
    private CustomerService customerService;

    @Autowired
    private AssignmentService assignmentService;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket) {
        ticketRepository.save(ticket);
        customerService.saveTicket(ticket);
        assignmentService.save(ticket);
        return ticket;

    }

    public int getNumberForOneMovie(String movie) {
        return ticketRepository.findAllByMovieIgnoreCase(movie).size();
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
