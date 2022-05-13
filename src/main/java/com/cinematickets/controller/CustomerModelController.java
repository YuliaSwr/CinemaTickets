package com.cinematickets.controller;

import com.cinematickets.entity.Customer;
import com.cinematickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/customer")
public class CustomerModelController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public String register(Model model, @ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerService.getAll());
        return "admin-panel-customers";
    }

    @PostMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/admin/customer";
    }
}
