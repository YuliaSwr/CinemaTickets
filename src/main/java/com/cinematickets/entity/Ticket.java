package com.cinematickets.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
