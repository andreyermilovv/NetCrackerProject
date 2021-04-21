package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.AirplaneDto;
import com.netcracker.airlines.entities.Airplane;

import java.util.List;

public interface AirplaneService {

    Airplane getOne(Long id);

    List<Airplane> getAll();

    void save(AirplaneDto airportDto);

    void delete(Long id);

    void edit(Long id, AirplaneDto airportDto);
}
