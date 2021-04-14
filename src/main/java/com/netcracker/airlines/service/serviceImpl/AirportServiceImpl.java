package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.mapper.AirportMapper;
import com.netcracker.airlines.repository.AirportRepo;
import com.netcracker.airlines.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepo airportRepo;

    private final AirportMapper airportMapper;

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
        Airport airport = airportMapper.toCreate(airportDto);
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
        Airport edited = airportMapper.toEdit(airportDto, airport);
        airportRepo.save(edited);
    }

}
