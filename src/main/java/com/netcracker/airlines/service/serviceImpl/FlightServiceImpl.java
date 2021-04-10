package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.IncorrectFlightStatusException;
import com.netcracker.airlines.repository.AirportRepo;
import com.netcracker.airlines.repository.FlightRepo;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepo;

    private final AirportRepo airportRepo;

    @Override
    public Flight getOne(Long id) {
        return flightRepo.getOne(id);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepo.findAll();
    }

    @Override
    public void save(FlightDto flightDto) {
        if (flightDto.getDestination().equals(flightDto.getDeparture())) {
            throw new IllegalArgumentException("Destination and departure airports can't be the same");
        }
        if (flightDto.getTimeArrival().isBefore(flightDto.getTimeDeparture())) {
            throw new IllegalArgumentException("Time of arrival must be after time of departure");
        }
        Flight flight = new Flight(airportRepo.getOne(flightDto.getDeparture()),
                airportRepo.getOne(flightDto.getDestination()),
                flightDto.getDate(),
                flightDto.getTimeDeparture(),
                flightDto.getTimeArrival(),
                flightDto.getStatus());
        flightRepo.save(flight);
    }

    @Override
    public void createTemplate(FlightTemplateDto flightTemplateDto) {

    }

    @Override
    public void confirmFlight(FlightDto flightDto) {

    }

    @Override
    public void delete(Long id) {
        Flight flight = getOne(id);
        flightRepo.delete(flight);
    }

    @Override
    public void edit(Long id, FlightEditDto flightDto) {
        if (flightDto.getTimeArrival().isBefore(flightDto.getTimeDeparture())) {
            throw new IllegalArgumentException("Time of arrival must be after time of departure");
        }
        Flight flight = getOne(id);
        flight.setDate(flightDto.getDate());
        flight.setTimeDeparture(flightDto.getTimeDeparture());
        flight.setTimeArrival(flightDto.getTimeArrival());
        flight.setStatus(flightDto.getStatus());
    }

    private void checkCorrectnessOfStatus(Status currentStatus, Status... requiredStatuses) {
        boolean isCorrect = Arrays.stream(requiredStatuses)
                .anyMatch(status -> status == currentStatus);

        if (!isCorrect) {
            throw new IncorrectFlightStatusException(currentStatus);
        }
    }
}
