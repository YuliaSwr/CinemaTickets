package com.cinematickets.entity;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Customer {

    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Id
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

//    @Nullable
//    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
//    private List<Ticket> tickets;

    @Nullable
    @ElementCollection
    private List<Long> tickets;

    @Nullable
    public String getTicketsString() {
        String result = "";
        for (Long id: tickets) {
            result = result + id + "\n";
        }
        return result;
    }
}
