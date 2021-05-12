package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.*;
import com.netcracker.airlines.dto.search.FlightSearchDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Category;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.repository.AirplaneRepo;
import com.netcracker.airlines.repository.AirportRepo;
import com.netcracker.airlines.repository.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FlightMapper {

    private final AirportRepo airportRepo;

    private final AirplaneRepo airplaneRepo;

    private final AirplaneMapper airplaneMapper;

    private final AirportMapper airportMapper;

    private final TicketRepo ticketRepo;

    public Flight toTemplate(FlightTemplateDto flightTemplateDto) {
        Flight flight = new Flight(airportRepo.getOne(flightTemplateDto.getDeparture()),
                airportRepo.getOne(flightTemplateDto.getDestination()),
                flightTemplateDto.getTimeDeparture(),
                airplaneRepo.getOne(flightTemplateDto.getAirplane()),
                flightTemplateDto.getTimeArrival());
        flight.setStatus(Status.TEMPLATE);
        return flight;
    }

    public Flight toUpcoming(FlightDto flightDto) {
        Flight flight = new Flight(airportRepo.getOne(flightDto.getDeparture()),
                airportRepo.getOne(flightDto.getDestination()),
                flightDto.getTimeDeparture(),
                airplaneRepo.getOne(flightDto.getAirplane()),
                flightDto.getTimeArrival());
        flight.setStatus(Status.UPCOMING);
        return flight;
    }

    public Flight toEdit(FlightEditDto flightEditDto, Flight target) {
        target.setTimeDeparture(flightEditDto.getTimeDeparture());
        target.setTimeArrival(flightEditDto.getTimeArrival());
        target.setStatus(flightEditDto.getStatus());
        return target;
    }

    public Flight toEditTemplate(EditTemplateDto editTemplateDto, Flight target) {
        target.setTimeDeparture(editTemplateDto.getTimeDeparture());
        target.setTimeArrival(editTemplateDto.getTimeArrival());
        target.setDeparture(airportRepo.getOne(editTemplateDto.getDeparture()));
        target.setDestination(airportRepo.getOne(editTemplateDto.getDestination()));
        target.setAirplane(airplaneRepo.getOne(editTemplateDto.getAirplane()));
        return target;
    }

    public FlightSearchDto toSearchDto(String departureCountry,
                                       String departureCity,
                                       String destinationCountry,
                                       String destinationCity,
                                       LocalDate date,
                                       Integer cost) {
        FlightSearchDto flightSearchDto = new FlightSearchDto();
        if (!departureCity.isBlank()) flightSearchDto.setDepartureCity(departureCity);
        if (!destinationCity.isBlank()) flightSearchDto.setDestinationCity(destinationCity);
        if (!departureCountry.isBlank()) flightSearchDto.setDepartureCountry(departureCountry);
        if (!destinationCountry.isBlank()) flightSearchDto.setDestinationCountry(destinationCountry);
        if (date != null) flightSearchDto.setDate(date);
        if (cost != null) flightSearchDto.setCost(cost);
        return flightSearchDto;
    }

    public FlightDtoResponse toResponse(Flight flight){
        FlightDtoResponse flightDtoResponse = new FlightDtoResponse();
        flightDtoResponse.setId(flight.getId());
        flightDtoResponse.setDeparture(airportMapper.toDto(flight.getDeparture()));
        flightDtoResponse.setDestination(airportMapper.toDto(flight.getDestination()));
        flightDtoResponse.setAirplane(airplaneMapper.toDto(flight.getAirplane()));
        flightDtoResponse.setTimeArrival(flight.getTimeArrival());
        flightDtoResponse.setTimeDeparture(flight.getTimeDeparture());
        List<Integer> tickets = new ArrayList<>();
        tickets.add(ticketRepo.findByCategoryAndFlightOrderByIdAsc(Category.ECONOMY, flight).size());
        tickets.add(ticketRepo.findByCategoryAndFlightOrderByIdAsc(Category.BUSINESS, flight).size());
        tickets.add(ticketRepo.findByCategoryAndFlightOrderByIdAsc(Category.FIRST, flight).size());
        flightDtoResponse.setTickets(tickets);
        return flightDtoResponse;
    }
}
