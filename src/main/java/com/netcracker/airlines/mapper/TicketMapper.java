package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.*;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.repository.FlightRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final FlightRepo flightRepo;

    public Ticket toCreate(TicketDto ticketDto){
        return new Ticket(flightRepo.getOne(ticketDto.getFlight()), ticketDto.getCategory(), ticketDto.getSeats(), ticketDto.getCost());
    }

    public Ticket toEdit(TicketEditDto ticketEditDto, Ticket target){
        target.setCost(ticketEditDto.getCost());
        target.setSeats(ticketEditDto.getSeats());
        return target;
    }
}
