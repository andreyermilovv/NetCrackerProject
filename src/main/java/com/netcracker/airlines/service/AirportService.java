package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;

import java.util.List;

public interface AirportService {
    Airport getOne(Long id);

    List<Airport> getAll();

    void save(AirportDto airportDto);

    void delete(Long id);

    void edit(Long id, AirportDto airportDto);
}
