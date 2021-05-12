package com.netcracker.airlines.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "purchase_details",
            joinColumns = { @JoinColumn(name = "ticket_id") },
            inverseJoinColumns = { @JoinColumn(name = "purchase_id") }
    )
    private List<Ticket> ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer cost;

    public Purchase(List<Ticket> ticket, User user, Integer cost) {
        this.ticket = ticket;
        this.user = user;
        this.cost = cost;
    }
}
