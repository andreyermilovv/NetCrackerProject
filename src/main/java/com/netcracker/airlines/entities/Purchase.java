package com.netcracker.airlines.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer amount;

    private Integer cost;

    public Purchase(Ticket ticket, User user, Integer amount, Integer cost) {
        this.ticket = ticket;
        this.user = user;
        this.amount = amount;
        this.cost = cost;
    }
}
