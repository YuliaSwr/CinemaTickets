package com.cinematickets.controller;

import com.cinematickets.entity.Ticket;
import com.cinematickets.service.CustomerService;
import com.cinematickets.service.OperatorService;
import com.cinematickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketModelController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CustomerService customerService;

    List<String> MOVIES = Arrays.asList("LaLaLand", "Titanic", "GreenBook", "KillBill", "JohnWick", "Deadpool");

    @PostMapping()
    public String save(Model model, @ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/ticket";
    }

    @GetMapping()
    public String getAll(Model model) {
        for (String movie : MOVIES) {
            model.addAttribute("number_" + movie, ticketService.getNumberForOneMovie(movie));
        }
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("tickets",ticketService.getAll());
        return "admin-panel-tickets";
    }

    @PostMapping( "/delete/{ticketId}")
    public String deleteById(@PathVariable Long ticketId) {
        ticketService.deleteById(ticketId);
        return "redirect:/ticket";
    }
}
