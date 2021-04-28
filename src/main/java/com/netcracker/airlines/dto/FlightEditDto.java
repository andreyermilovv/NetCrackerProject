package com.netcracker.airlines.dto;

import com.netcracker.airlines.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightEditDto {

    @Future(message = "Date can't be past")
    private LocalDate date;


    private LocalDateTime timeDeparture;


    private LocalDateTime timeArrival;

    @NotNull(message = "Status can't be null")
    private Status status;
}
