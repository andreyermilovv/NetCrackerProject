package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.*;
import com.netcracker.airlines.dto.search.FlightSearchDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Category;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.IncorrectFlightStatusException;
import com.netcracker.airlines.mapper.FlightMapper;
import com.netcracker.airlines.repository.FlightRepo;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.netcracker.airlines.repository.FlightSpecificationsUtils.*;

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
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < flight.getAirplane().getFirst(); i++) {
            tickets.add(new Ticket(flight, Category.FIRST, i, flightDto.getFirst(), true));
        }
        for (int i = 0; i < flight.getAirplane().getBusiness(); i++) {
            tickets.add(new Ticket(flight, Category.BUSINESS, i, flightDto.getBusiness(), true));
        }
        for (int i = 0; i < flight.getAirplane().getEconomic(); i++) {
            tickets.add(new Ticket(flight, Category.ECONOMY, i, flightDto.getEconomic(), true));
        }
        ticketRepo.saveAll(tickets);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(Role.ADMIN)) {
            specifications.add(flightStatus(Arrays.asList(Status.UPCOMING, Status.CANCELLED, Status.PAST)));
        }
        else {
            specifications.add(flightStatus(Collections.singletonList(Status.UPCOMING)));
        }
        Specification<Flight> specification = specifications.stream().reduce(Specification::and).orElse(null);
        List<FlightDtoResponse> responses = new ArrayList<>();
        for (Flight flight : flightRepo.findAll(specification)) {
            responses.add(flightMapper.toResponse(flight));
        }
        if (flightSearchDto.getCost() != null)
            responses = responses.stream()
                    .filter(x -> ticketRepo.findByCategoryAndFlightOrderByIdAsc(Category.ECONOMY, getOne(x.getId())).get(0).getCost() < flightSearchDto.getCost())
                    .collect(Collectors.toList());
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
