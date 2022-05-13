package com.cinematickets.controller;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.AssignmentStatus;
import com.cinematickets.entity.Customer;
import com.cinematickets.entity.EditAssignment;
import com.cinematickets.service.AssignmentService;
import com.cinematickets.service.CustomerService;
import com.cinematickets.service.OperatorService;
import com.cinematickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignment")
public class AssignmentModelController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private OperatorService operatorService;

    @PostMapping()
      public String edit(Model model, @ModelAttribute EditAssignment editAssignment) {
        assignmentService.edit(editAssignment);
        operatorService.setNonAvailable(editAssignment.getOperatorId());
        return "redirect:/assignment";
    }

    @GetMapping()
    public String getAll(Model model) {

        model.addAttribute("available_operators", operatorService.getAllAvailable());
        model.addAttribute("waiting_assignments", assignmentService.getByStatus(AssignmentStatus.WAITING));
        model.addAttribute("edit_assignment", new EditAssignment());

        model.addAttribute("amount_waiting", assignmentService.getByStatus(AssignmentStatus.WAITING).size());
        model.addAttribute("amount_inprocess", assignmentService.getByStatus(AssignmentStatus.IN_PROCESS).size());
        model.addAttribute("amount_done", assignmentService.getByStatus(AssignmentStatus.DONE).size());

        model.addAttribute("assignment", new Assignment());
        model.addAttribute("assignments", assignmentService.getAll());
        return "admin-panel-assignments";
    }

    @PostMapping( "/delete/{assignmentId}")
    public String deleteById(@PathVariable Long assignmentId) {
        assignmentService.deleteById(assignmentId);
        return "redirect:/assignment";
    }
}
