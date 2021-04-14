package com.netcracker.airlines.dto;

import com.netcracker.airlines.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    @NotNull(message = "Id can't be null")
    private Long id;

    @NotNull(message = "Departure can't be null")
    private Long departure;

    @NotNull(message = "Destination can't be null")
    private Long destination;

    @NotNull(message = "Date can't be null")
    @Future(message = "Date can't be past")
    private LocalDate date;

    @NotNull(message = "Departure time can't be null")
    private LocalTime timeDeparture;

    @NotNull(message = "Arrival time can't be null")
    private LocalTime timeArrival;

    @NotNull(message = "Status can't be null")
    private Status status;
}
