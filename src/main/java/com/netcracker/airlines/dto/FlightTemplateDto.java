package com.netcracker.airlines.dto;

import com.netcracker.airlines.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTemplateDto {

    @NotNull
    private Long departure;

    @NotNull
    private Long destination;

    private LocalDate date;

    private LocalTime timeDeparture;

    private LocalTime timeArrival;
}
