package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.EditTemplateDto;
import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.entities.Airplane;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.repository.AirplaneRepo;
import com.netcracker.airlines.repository.AirportRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FlightMapper {

    private final AirportRepo airportRepo;

    private final AirplaneRepo airplaneRepo;

    public Flight toTemplate(FlightTemplateDto flightTemplateDto){
        Flight flight = new Flight(airportRepo.getOne(flightTemplateDto.getDeparture()),
                airportRepo.getOne(flightTemplateDto.getDestination()),
                flightTemplateDto.getDate(),
                flightTemplateDto.getTimeDeparture(),
                airplaneRepo.getOne(flightTemplateDto.getAirplane()),
                flightTemplateDto.getTimeArrival());
        flight.setStatus(Status.TEMPLATE);
        return flight;
    }

    public Flight toUpcoming(FlightDto flightDto){
        Flight flight = new Flight(airportRepo.getOne(flightDto.getDeparture()),
                airportRepo.getOne(flightDto.getDestination()),
                flightDto.getDate(),
                flightDto.getTimeDeparture(),
                airplaneRepo.getOne(flightDto.getAirplane()),
                flightDto.getTimeArrival());
        flight.setStatus(Status.UPCOMING);
        return flight;
    }

    public Flight toEdit(FlightEditDto flightEditDto, Flight target){
        target.setDate(flightEditDto.getDate());
        target.setTimeDeparture(flightEditDto.getTimeDeparture());
        target.setTimeArrival(flightEditDto.getTimeArrival());
        target.setStatus(flightEditDto.getStatus());
        return target;
    }

    public Flight toEditTemplate(EditTemplateDto editTemplateDto, Flight target){
        target.setDate(editTemplateDto.getDate());
        target.setTimeDeparture(editTemplateDto.getTimeDeparture());
        target.setTimeArrival(editTemplateDto.getTimeArrival());
        target.setDeparture(airportRepo.getOne(editTemplateDto.getDeparture()));
        target.setDestination(airportRepo.getOne(editTemplateDto.getDestination()));
        target.setAirplane(airplaneRepo.getOne(editTemplateDto.getAirplane()));
        return target;
    }
}
