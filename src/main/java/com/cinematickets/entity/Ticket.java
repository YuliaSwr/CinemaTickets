package com.cinematickets.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    @Id
    private Long id;

    @NotNull
    private String movie;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TicketType type;

    private String date;

    private String time;

    @NotNull
    private String customerEmail;

    @Enumerated(value = EnumType.STRING)
    private AssignmentStatus status = AssignmentStatus.WAITING;
}
