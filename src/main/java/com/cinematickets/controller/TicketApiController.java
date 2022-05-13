package com.cinematickets.controller;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.Customer;
import com.cinematickets.entity.Ticket;
import com.cinematickets.service.AssignmentService;
import com.cinematickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketApiController {

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @PostMapping()
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }
}
