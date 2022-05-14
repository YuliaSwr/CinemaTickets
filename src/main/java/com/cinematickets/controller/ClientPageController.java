package com.cinematickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientPageController {

    @GetMapping("/booking")
    public String bookingPage(Model model) {
        return "ddd";
    }
}
