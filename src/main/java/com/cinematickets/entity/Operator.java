package com.cinematickets.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Operator {

    @SequenceGenerator(
            name = "operator_sequence",
            sequenceName = "operator_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "operator_sequence"
    )
    @Id
    private Long id;

    @NotNull
    private String employeeCode;

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    private Boolean isAvailable = true;
}
