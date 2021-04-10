package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.repository.AirportRepo;
import com.netcracker.airlines.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepo airportRepo;

    @Override
    public Airport getOne(Long id) {
        return airportRepo.getOne(id);
    }

    @Override
    public List<Airport> getAll() {
        return airportRepo.findAll();
    }

    @Override
    public void save(AirportDto airportDto) {
        Airport airport = new Airport(airportDto.getName(), airportDto.getCountry(), airportDto.getCity());
        airportRepo.save(airport);
    }

    @Override
    public void delete(Long id) {
        Airport airport = getOne(id);
        airportRepo.delete(airport);
    }

    @Override
    public void edit(Long id, AirportDto airportDto) {
        Airport airport = getOne(id);
        airport.setName(airportDto.getName());
        airport.setCity(airportDto.getCity());
        airport.setCountry(airportDto.getCountry());
        airportRepo.save(airport);
    }

}
