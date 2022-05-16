package com.cinematickets.service;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.AssignmentStatus;
import com.cinematickets.entity.EditAssignment;
import com.cinematickets.entity.Ticket;
import com.cinematickets.repository.AssignmentRepository;
import com.cinematickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OperatorService operatorService;

    public Assignment save(Ticket ticket) {
        assignmentRepository.findByTicketId(ticket.getId())
                .ifPresent((s) -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assignment for this ticket is already in system");
                });

        Assignment assignment = Assignment.builder()
                .ticketId(ticket.getId())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .status(AssignmentStatus.WAITING)
                .build();

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    public Assignment getByTicketId(Long ticketId) {
        return assignmentRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assignment for this ticket is NOT in system"));
    }

    public List<Assignment> getByStatus(AssignmentStatus status) {
        return assignmentRepository.findAllByStatus(status);
    }

    public void edit(EditAssignment request) {
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assignment is NOT in system"));

        assignment.setOperatorId(request.getOperatorId());
        assignment.setStatus(AssignmentStatus.IN_PROCESS);
        ticketRepository.findById(assignment.getTicketId()).get().setStatus(AssignmentStatus.IN_PROCESS);

        assignmentRepository.save(assignment);
    }

    public void deleteById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assignment is NOT in system"));

        if (assignment.getOperatorId() != null) {
            operatorService.getById(assignment.getOperatorId()).setIsAvailable(true);
        }

        assignmentRepository.deleteById(id);
    }

    public void setAsDone(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assignment is NOT in system"));

        if (!assignment.getStatus().equals(AssignmentStatus.IN_PROCESS)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This assignment is NOT in process");
        }

        operatorService.getById(assignment.getOperatorId()).setIsAvailable(true);

        assignment.setStatus(AssignmentStatus.DONE);
        ticketRepository.findById(assignment.getTicketId()).get().setStatus(AssignmentStatus.DONE);
        assignmentRepository.save(assignment);
    }
}
