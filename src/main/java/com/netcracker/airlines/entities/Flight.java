package com.netcracker.airlines.entities;

import com.netcracker.airlines.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalDate date;

    private LocalTime timeDeparture;

    private LocalTime timeArrival;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Flight(Airport departure,
                  Airport destination,
                  LocalDate date,
                  LocalTime timeDeparture,
                  LocalTime timeArrival,
                  Airplane airplane,
                  Status status) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.status = status;
        this.airplane = airplane;
    }

    public Flight(Airport departure,
                  Airport destination,
                  LocalDate date,
                  LocalTime timeDeparture,
                  Airplane airplane,
                  LocalTime timeArrival) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.airplane = airplane;
    }
}
