package com.cinematickets.controller;

import com.cinematickets.entity.Ticket;
import com.cinematickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ticket")
public class TicketApiController {

    @Autowired
    private TicketService ticketService;

    @PostMapping()
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping()
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @PostMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
        return "Ticket successful deleted!";
    }

}
