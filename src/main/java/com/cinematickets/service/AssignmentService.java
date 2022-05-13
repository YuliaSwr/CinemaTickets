package com.cinematickets.service;

import com.cinematickets.entity.Assignment;
import com.cinematickets.entity.AssignmentStatus;
import com.cinematickets.entity.EditAssignment;
import com.cinematickets.entity.Ticket;
import com.cinematickets.repository.AssignmentRepository;
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

    public Assignment save(Ticket ticket) {
        if (assignmentRepository.findByTicketId(ticket.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assigment for this ticket is already in system");
        }

        Assignment assignment = Assignment.builder()
                .ticketId(ticket.getId())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .status(AssignmentStatus.WAITING)
                .build();

        return assignmentRepository.save(assignment);
    }

    public Assignment getByTicketId(Long ticketId) {
         assignmentRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The assigment for this ticket is NOT in system"));
        return assignmentRepository.findByTicketId(ticketId).get();
    }

    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> getByStatus(AssignmentStatus status) {
        return assignmentRepository.findAllByStatus(status);
    }

    public void deleteById(Long ticketId) {
        assignmentRepository.deleteByTicketId(ticketId);
    }

    public void edit(EditAssignment request) {
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId()).get();

        assignment.setOperatorId(request.getOperatorId());
        assignment.setStatus(AssignmentStatus.IN_PROCESS);

        assignmentRepository.save(assignment);
    }
}
