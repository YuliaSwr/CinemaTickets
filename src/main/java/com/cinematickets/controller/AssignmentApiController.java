package com.cinematickets.controller;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.EditAssignment;
import com.cinematickets.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/assignment")
public class AssignmentApiController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping()
    public String edit(@RequestBody EditAssignment editAssignment) {
        assignmentService.edit(editAssignment);
        return "redirect:/admin/assignment";
    }

    @GetMapping()
    public List<Assignment> getAll() {
        return assignmentService.getAll();
    }

    @PostMapping( "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        assignmentService.deleteById(id);
        return "redirect:/admin/assignment";
    }
}
