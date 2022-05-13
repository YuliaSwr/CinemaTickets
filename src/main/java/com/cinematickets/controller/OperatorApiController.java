package com.cinematickets.controller;

import com.cinematickets.entity.Operator;
import com.cinematickets.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/operator")
public class OperatorApiController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping()
    public Operator register(@RequestBody Operator operator) {
        return operatorService.save(operator);
    }

    @GetMapping()
    public List<Operator> getAll() {
        return operatorService.getAll();
    }

    @PostMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        operatorService.deleteById(id);
        return "Operator successful deleted!";
    }
}

