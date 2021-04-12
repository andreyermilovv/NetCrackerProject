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

    @NotNull
    private Long id;

    @NotNull
    private Long departure;

    @NotNull
    private Long destination;

    @Future
    private LocalDate date;

    @Future
    private LocalTime timeDeparture;

    @Future
    private LocalTime timeArrival;

    @NotNull
    private Status status;
}
