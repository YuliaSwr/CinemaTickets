package com.cinematickets.controller;

import com.cinematickets.entity.Operator;
import com.cinematickets.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operator")
public class OperatorApiController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping()
    public void save(@ModelAttribute Operator operator) {
        operatorService.save(operator);
    }

    @GetMapping()
    public List<Operator> getAll() {
        return operatorService.getAll();
    }
}

