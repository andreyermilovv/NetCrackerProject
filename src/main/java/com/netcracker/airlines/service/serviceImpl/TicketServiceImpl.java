package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.TicketDto;
import com.netcracker.airlines.dto.TicketEditDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.repository.FlightRepo;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;

    private final FlightRepo flightRepo;

    @Override
    public Ticket getOne(Long id) {
        return ticketRepo.getOne(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    @Override
    public List<Ticket> findByFlight(Flight flight) {
        return ticketRepo.findByFlight(flight);
    }

    @Override
    public void save(TicketDto ticketDto) {
        Ticket ticket = new Ticket(flightRepo.getOne(ticketDto.getFlight()), ticketDto.getCategory(), ticketDto.getSeats(), ticketDto.getCost());
        ticketRepo.save(ticket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = getOne(id);
        ticketRepo.delete(ticket);
    }

    @Override
    public void edit(Long id, TicketEditDto ticket) {

    }
}
