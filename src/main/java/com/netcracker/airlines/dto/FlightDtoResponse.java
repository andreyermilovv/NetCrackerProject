package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDtoResponse {

    private Long id;

    private AirportDto departure;

    private AirportDto destination;

    private AirplaneDto airplane;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrival;

    private List<Integer> tickets;
}
