package com.cinematickets.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class Ticket implements Serializable {

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
}
