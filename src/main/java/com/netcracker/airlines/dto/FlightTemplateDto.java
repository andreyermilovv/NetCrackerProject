package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTemplateDto {

    @NotNull(message = "Departure can't be null")
    private Long departure;

    @NotNull(message = "Destination can't be null")
    private Long destination;

    @NotNull(message = "Airplane can't be null")
    private Long airplane;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrival;
}
