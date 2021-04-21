package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public Airport toCreate(AirportDto airportDto){
        return new Airport(airportDto.getName(), airportDto.getCountry(), airportDto.getCity());
    }

    public Airport toEdit(AirportDto airportDto, Airport airport){
        airport.setName(airportDto.getName());
        airport.setCity(airportDto.getCity());
        airport.setCountry(airportDto.getCountry());
        return airport;
    }
}
