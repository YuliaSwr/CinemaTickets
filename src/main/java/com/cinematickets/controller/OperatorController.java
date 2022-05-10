package com.cinematickets.controller;

import com.cinematickets.entity.Operator;
import com.cinematickets.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping()
    public Operator save(@RequestBody Operator operator) {
        return operatorService.save(operator);
    }
}

