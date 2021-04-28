package com.netcracker.airlines.entities;

import com.netcracker.airlines.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departure;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrival;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

    public Flight(Airport departure,
                  Airport destination,
                  LocalDateTime timeDeparture,
                  LocalDateTime timeArrival,
                  Airplane airplane,
                  Status status) {
        this.departure = departure;
        this.destination = destination;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.status = status;
        this.airplane = airplane;
    }

    public Flight(Airport departure,
                  Airport destination,
                  LocalDateTime timeDeparture,
                  Airplane airplane,
                  LocalDateTime timeArrival) {
        this.departure = departure;
        this.destination = destination;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.airplane = airplane;
    }
}
