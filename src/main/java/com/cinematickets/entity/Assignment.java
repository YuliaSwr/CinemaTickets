package com.cinematickets.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Assignment {

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

    private String date;

    private Long ticketId;

    @Nullable
    private Long operatorId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private AssignmentStatus status;

//    @OneToOne()
//    private Ticket ticket;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "operator_id", referencedColumnName = "id")
//    private Operator operator;
}
