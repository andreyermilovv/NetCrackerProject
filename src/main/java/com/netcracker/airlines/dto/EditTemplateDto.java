package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTemplateDto {

    @NotNull(message = "Departure can't be null")
    private Long departure;

    @NotNull(message = "Destination can't be null")
    private Long destination;

    private LocalDate date;

    private LocalTime timeDeparture;

    private LocalTime timeArrival;

    private Long airplane;
}
