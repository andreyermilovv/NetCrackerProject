package com.netcracker.airlines.mapper;

import com.netcracker.airlines.dto.*;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.repository.FlightRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final FlightRepo flightRepo;

    public List<Ticket> toCreate(TicketDto ticketDto) {
        List<Ticket> res = new ArrayList<>();
        for (int i = 0; i < ticketDto.getSeats(); i++) {
            res.add(new Ticket(flightRepo.getOne(ticketDto.getFlight()), ticketDto.getCategory(), i, ticketDto.getCost(), true));
        }
        return res;
    }

    public Ticket toEdit(TicketEditDto ticketEditDto, Ticket target) {
        target.setCost(ticketEditDto.getCost());
        target.setSeats(ticketEditDto.getSeats());
        return target;
    }
}
