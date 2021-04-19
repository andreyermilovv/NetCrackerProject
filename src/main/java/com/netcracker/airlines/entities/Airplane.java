package com.netcracker.airlines.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer busyness;

    private Integer economic;

    private Integer first;

    public Airplane(String name, Integer busyness, Integer economic, Integer first) {
        this.name = name;
        this.busyness = busyness;
        this.economic = economic;
        this.first = first;
    }
}
