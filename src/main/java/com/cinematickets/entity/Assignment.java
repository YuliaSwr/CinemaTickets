package com.cinematickets.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public boolean isInProcess() {
        return status.equals(AssignmentStatus.IN_PROCESS);
    }
}
