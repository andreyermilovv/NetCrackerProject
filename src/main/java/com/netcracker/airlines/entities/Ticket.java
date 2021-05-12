package com.netcracker.airlines.entities;

import com.netcracker.airlines.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer seats;

    private Integer cost;

    private Boolean available;

    public Ticket(Flight flight, Category category, Integer seats, Integer cost, Boolean available) {
        this.flight = flight;
        this.category = category;
        this.seats = seats;
        this.cost = cost;
        this.available = available;
    }
}
