package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.TicketDto;
import com.netcracker.airlines.dto.TicketEditDto;
import com.netcracker.airlines.entities.Airplane;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.mapper.TicketMapper;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;

    private final TicketMapper ticketMapper;

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
        Ticket ticket = ticketMapper.toCreate(ticketDto);
        checkMaxCapacity(ticket);
        if (ticketRepo.findByCategoryAndFlight(ticket.getCategory(), ticket.getFlight())!=null) throw new IllegalArgumentException("There is already tickets with selected category");
        ticketRepo.save(ticket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = getOne(id);
        ticketRepo.delete(ticket);
    }

    @Override
    public void edit(Long id, TicketEditDto ticket) {
        Ticket edited = ticketMapper.toEdit(ticket, getOne(id));
        checkMaxCapacity(edited);
        ticketRepo.save(edited);
    }

    private void checkMaxCapacity(Ticket ticket){
        Airplane airplane = ticket.getFlight().getAirplane();
        switch (ticket.getCategory()){
            case ECONOMY -> {
                if (ticket.getSeats() > airplane.getEconomic()) throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
            case BUSINESS -> {
                if (ticket.getSeats() > airplane.getBusyness()) throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
            case FIRST -> {
                if (ticket.getSeats() > airplane.getFirst()) throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
        }
    }
}
