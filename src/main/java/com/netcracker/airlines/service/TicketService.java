package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.TicketDto;
import com.netcracker.airlines.dto.TicketEditDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;

import java.util.List;

public interface TicketService {

    Ticket getOne(Long id);

    List<Ticket> getAll();

    List<Ticket> findByFlight(Flight flight);

    void save(TicketDto ticket);

    void delete(Long id);

    void edit(Long id, TicketEditDto ticket);
}
