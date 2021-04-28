package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.*;
import com.netcracker.airlines.dto.search.FlightSearchDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Category;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.IncorrectFlightStatusException;
import com.netcracker.airlines.mapper.FlightMapper;
import com.netcracker.airlines.repository.FlightRepo;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.netcracker.airlines.repository.FlightSpecificationsUtils.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<Flight> getWhenStatusNot(Status status) {
        return flightRepo.findByStatusNot(status);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepo.findAll();
    }

    @Override
    public void createTemplate(FlightTemplateDto flightTemplateDto) {
        Flight flight = flightMapper.toTemplate(flightTemplateDto);
        checkAirports(flight);
        if (flight.getTimeArrival() != null && flight.getTimeDeparture() != null) checkTime(flight);
        flightRepo.save(flight);
    }

    @Override
    public void confirmFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toUpcoming(flightDto);
        checkAirports(flight);
        checkTime(flight);
        flightRepo.save(flight);
        ticketRepo.save(new Ticket(flight, Category.ECONOMY, flight.getAirplane().getEconomic(), flightDto.getEconomic()));
        ticketRepo.save(new Ticket(flight, Category.BUSINESS, flight.getAirplane().getBusiness(), flightDto.getBusiness()));
        ticketRepo.save(new Ticket(flight, Category.FIRST, flight.getAirplane().getFirst(), flightDto.getFirst()));
    }

    @Override
    public void delete(Long id) {
        Flight flight = getOne(id);
        checkCorrectnessOfStatus(flight.getStatus(), Status.TEMPLATE);
        flightRepo.delete(flight);
    }

    @Override
    public void edit(Long id, FlightEditDto flightDto) {
        Flight edited = flightMapper.toEdit(flightDto, flightRepo.getOne(id));
        checkCorrectnessOfStatus(flightDto.getStatus(), Status.UPCOMING);
        checkAirports(edited);
        checkTime(edited);
        flightRepo.save(edited);
    }

    @Override
    public void editTemplate(Long id, EditTemplateDto editTemplateDto) {
        Flight edited = flightMapper.toEditTemplate(editTemplateDto, flightRepo.getOne(id));
        checkAirports(edited);
        if (edited.getTimeArrival() != null & edited.getTimeDeparture() != null) checkTime(edited);
        flightRepo.save(edited);
    }

    @Override
    public void updateStatus() {
        List<Flight> flights = flightRepo.findByStatus(Status.UPCOMING);
        for (Flight flight : flights) {
            if (flight.getTimeDeparture().plusHours(1).isAfter(LocalDateTime.now())) {
                flight.setStatus(Status.PAST);
            }
        }
    }

    @Override
    public List<FlightDtoResponse> search(FlightSearchDto flightSearchDto) {
        List<Specification<Flight>> specifications = new ArrayList<>();
        if (flightSearchDto.getDate() != null)
            specifications.add(flightDate(flightSearchDto.getDate()));
        if (flightSearchDto.getDepartureCity() != null) {
            specifications.add(flightDepartureCity(flightSearchDto.getDepartureCity()));
        }
        if (flightSearchDto.getDepartureCountry() != null) {
            specifications.add(flightDepartureCountry(flightSearchDto.getDepartureCountry()));
        }
        if (flightSearchDto.getDestinationCity() != null) {
            specifications.add(flightDestinationCity(flightSearchDto.getDestinationCity()));
        }
        if (flightSearchDto.getDestinationCountry() != null) {
            specifications.add(flightDestinationCountry(flightSearchDto.getDestinationCountry()));
        }
        Specification<Flight> specification = specifications.stream().reduce(Specification::and).orElse(null);
        List<FlightDtoResponse> responses = new ArrayList<>();
        for (Flight flight : flightRepo.findAll(specification)) {
            responses.add(flightMapper.toResponse(flight));
        }
        return responses;
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

    private void checkAirports(Flight flight) {
        if (flight.getDestination().equals(flight.getDeparture())) {
            throw new IllegalArgumentException("Destination and departure airports can't be the same");
        }
    }

    private void checkTime(Flight flight) {
        if (flight.getTimeArrival().isBefore(flight.getTimeDeparture())) {
            throw new IllegalArgumentException("Time of arrival must be after time of departure");
        }
    }
}
