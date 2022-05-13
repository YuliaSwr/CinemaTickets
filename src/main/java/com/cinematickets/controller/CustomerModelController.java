package com.cinematickets.controller;

import com.cinematickets.entity.Customer;
import com.cinematickets.entity.Operator;
import com.cinematickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerModelController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public String save(Model model, @ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerService.getAll());
        return "admin-panel-customers";
    }

    @PostMapping( "/delete/{customerId}")
    public String deleteByEmployeeCode(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
        return "redirect:/customer";
    }
}
