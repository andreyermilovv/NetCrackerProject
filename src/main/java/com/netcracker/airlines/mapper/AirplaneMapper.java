package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.AirplaneDto;
import com.netcracker.airlines.entities.Airplane;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {

    public Airplane toCreate(AirplaneDto airplaneDto){
        return new Airplane(airplaneDto.getName(), airplaneDto.getEconomic(), airplaneDto.getBusiness(), airplaneDto.getFirst());
    }

    public Airplane toEdit(AirplaneDto airportDto, Airplane airport){
        airport.setName(airportDto.getName());
        airport.setBusiness(airportDto.getBusiness());
        airport.setEconomic(airportDto.getEconomic());
        airport.setFirst(airportDto.getFirst());
        return airport;
    }

    public AirplaneDto toDto(Airplane airplane){
        return new AirplaneDto(airplane.getName(), airplane.getEconomic(), airplane.getBusiness(), airplane.getFirst());
    }
}
