package com.netcracker.airlines.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchDto {

    private String departureCountry;

    private String departureCity;

    private String destinationCountry;

    private String destinationCity;

    private LocalDate date;

    private Integer cost;
}
