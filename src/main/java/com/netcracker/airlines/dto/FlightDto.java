package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @NotNull(message = "Destination can't be null")
    private Long airplane;

    @NotNull(message = "Departure time can't be null")
    private LocalDateTime timeDeparture;

    @NotNull(message = "Arrival time can't be null")
    private LocalDateTime timeArrival;

    @NotNull(message = "Cost can't be null")
    @Min(value = 1, message = "Cost can't be zero")
    private Integer economic;

    @NotNull(message = "Cost can't be null")
    @Min(value = 1, message = "Cost can't be zero")
    private Integer business;

    @NotNull(message = "Cost can't be null")
    @Min(value = 1, message = "Cost can't be zero")
    private Integer first;
}
