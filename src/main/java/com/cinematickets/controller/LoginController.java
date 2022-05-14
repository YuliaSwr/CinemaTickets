package com.cinematickets.controller;


import com.cinematickets.entity.Customer;
import com.cinematickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/registration")
    public String signUp(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @PostMapping("/registration")
    public String sign(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect: /login";
    }

    @GetMapping("/login")
    public String customerLogin() {
        return "customer-login";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin-login";
    }

    @GetMapping("/cur")
    public String cur(@AuthenticationPrincipal Customer customer) {
        for (GrantedAuthority auth : customer.getAuthorities()) {
            if(auth.getAuthority().equals("CUSTOMER")) {
                return "redirect:/admin/assignment";
            }
        }
        return "redirect:/client/tickets";
    }
}
