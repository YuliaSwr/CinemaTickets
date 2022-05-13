package com.cinematickets.controller;

import com.cinematickets.entity.Operator;
import com.cinematickets.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operator")
public class OperatorModelController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping()
    public String save(Model model, @ModelAttribute Operator operator) {
        operatorService.save(operator);
        return "redirect:/operator";
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("operator", new Operator());
        model.addAttribute("operators", operatorService.getAll());
        return "admin-panel-operators";
    }

    @PostMapping( "/delete/{employeeCode}")
    public String deleteByEmployeeCode(@PathVariable String employeeCode) {
        operatorService.deleteByEmployeeCode(employeeCode);
        return "redirect:/operator";
    }
}
