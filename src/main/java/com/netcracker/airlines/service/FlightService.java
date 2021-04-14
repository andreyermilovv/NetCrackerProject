package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.EditTemplateDto;
import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;

import java.util.List;

public interface FlightService {

    Flight getOne(Long id);

    List<Flight> getByStatus(Status status);

    List<Flight> getAll();

    void save(FlightDto flightDto);

    void createTemplate(FlightTemplateDto flightTemplateDto);

    void confirmFlight(FlightDto flightDto);

    void delete(Long id);

    void edit(Long id, FlightEditDto flightDto);

    void cancel(Long id);

    void editTemplate(Long id, EditTemplateDto editTemplateDto);
}
