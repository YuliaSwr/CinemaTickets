package com.cinematickets.service;

import com.cinematickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

}
