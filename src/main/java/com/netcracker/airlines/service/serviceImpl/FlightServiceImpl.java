package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.IncorrectFlightStatusException;
import com.netcracker.airlines.mapper.FlightMapper;
import com.netcracker.airlines.repository.FlightRepo;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepo;

    private final TicketRepo ticketRepo;

    private final FlightMapper flightMapper;

    @Override
    public Flight getOne(Long id) {
        return flightRepo.getOne(id);
    }

    @Override
    public List<Flight> getByStatus(Status status) {
        return flightRepo.findByStatus(status);
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
        flightRepo.save(flightMapper.toUpcoming(flightDto));
    }

    @Override
    public void createTemplate(FlightTemplateDto flightTemplateDto) {
        if (flightTemplateDto.getDestination().equals(flightTemplateDto.getDeparture())) {
            throw new IllegalArgumentException("Destination and departure airports can't be the same");
        }
        if (flightTemplateDto.getTimeArrival().isBefore(flightTemplateDto.getTimeDeparture())) {
            throw new IllegalArgumentException("Time of arrival must be after time of departure");
        }
        Flight flight = flightMapper.toTemplate(flightTemplateDto);
        flightRepo.save(flight);
    }

    @Override
    public void confirmFlight(FlightDto flightDto) {
        checkCorrectnessOfStatus(flightDto.getStatus(), Status.TEMPLATE);
        if (ticketRepo.findByFlight(flightRepo.getOne(flightDto.getId())).size()==0){
            throw new IllegalArgumentException("There are no tickets for this flight");
        }
        Flight flight = flightMapper.toUpcoming(flightDto);
        flightRepo.save(flight);
    }

    @Override
    public void delete(Long id) {
        Flight flight = getOne(id);
        checkCorrectnessOfStatus(flight.getStatus(), Status.TEMPLATE);
        flightRepo.delete(flight);
    }

    @Override
    public void edit(Long id, FlightEditDto flightDto) {
        if (flightDto.getTimeArrival().isBefore(flightDto.getTimeDeparture())) {
            throw new IllegalArgumentException("Time of arrival must be after time of departure");
        }
        Flight flight = flightMapper.toEdit(flightDto, flightRepo.getOne(id));
        flightRepo.save(flight);
    }

    @Override
    public void cancel(Long id) {
        Flight flight = flightRepo.getOne(id);
        checkCorrectnessOfStatus(flight.getStatus(), Status.UPCOMING);
        flight.setStatus(Status.CANCELLED);
        flightRepo.save(flight);
    }

    private void checkCorrectnessOfStatus(Status currentStatus, Status requiredStatuses) {
        if (!currentStatus.equals(requiredStatuses)) {
            throw new IncorrectFlightStatusException(currentStatus);
        }
    }
}
