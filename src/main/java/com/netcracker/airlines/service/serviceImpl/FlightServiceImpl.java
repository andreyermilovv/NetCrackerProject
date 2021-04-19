package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.EditTemplateDto;
import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        ticketRepo.save(new Ticket(flight, Category.BUSINESS, flight.getAirplane().getBusyness(), flightDto.getBusyness()));
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
        for(Flight flight : flights){
            if (flight.getDate().isEqual(LocalDate.now()) && flight.getTimeDeparture().minusHours(1).isAfter(LocalTime.now())) {
                flight.setStatus(Status.PAST);
            }
        }
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
