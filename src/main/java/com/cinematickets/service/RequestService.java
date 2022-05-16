package com.cinematickets.service;

import com.cinematickets.entity.*;
import com.cinematickets.repository.CustomerRepository;
import com.cinematickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public void handleRequest(ClientRequest request) {
        Customer customer = Customer.builder()
                .email(request.getEmail())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        Ticket ticket = Ticket.builder()
                .movie(request.getMovie())
                .date(request.getDate())
                .time(request.getTime())
                .customerEmail(request.getEmail())
                .type(request.getType().equals("LUXE") ? TicketType.LUXE : TicketType.COMFORT)
                .status(AssignmentStatus.WAITING)
                .build();

        if (customerRepository.findByEmail(request.getEmail()).isEmpty()) {
            customerRepository.save(customer);
        }

        ticketRepository.save(ticket);
    }
}
