package com.cinematickets.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Assigment {

    @SequenceGenerator(
            name = "assigment_sequence",
            sequenceName = "assigment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assigment_sequence"
    )
    @Id
    private Long id;

    private Long ticketId;

    @OneToOne()
    private Ticket ticket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private Operator operator;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;
}
