package com.cinematickets.service;

import com.cinematickets.entity.Operator;
import com.cinematickets.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    public Operator save(Operator operator) {
        if (operatorRepository.findByEmployeeCode(operator.getEmployeeCode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This employee is already in system");
        }
        return operatorRepository.save(operator);
    }

    public Operator getByEmployeeCode(String employeeCode) {
        operatorRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This employee is NOT in system"));
        return operatorRepository.findByEmployeeCode(employeeCode).get();
    }

    public List<Operator> getAll() {
        return operatorRepository.findAll();
    }

    public Operator update(Operator newOperator) {
        Operator operator = operatorRepository.findByEmployeeCode(newOperator.getEmployeeCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is NOT in system"));
        operator.setIsAvailable(newOperator.getIsAvailable());
        operator.setName(newOperator.getName());
        return operatorRepository.save(operator);
    }

    public void deleteByEmail(String employeeCode) {
        operatorRepository.deleteByEmployeeCode(employeeCode);
    }
}
