package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.TicketDto;
import com.netcracker.airlines.dto.TicketEditDto;
import com.netcracker.airlines.entities.Airplane;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Category;
import com.netcracker.airlines.mapper.TicketMapper;
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
        List<Ticket> ticket = ticketMapper.toCreate(ticketDto);
        checkMaxCapacity(ticket.size(), flightRepo.getOne(ticketDto.getFlight()).getAirplane(), ticketDto.getCategory());
        if (ticketRepo.findByCategoryAndFlightOrderByIdAsc(ticketDto.getCategory(), flightRepo.getOne(ticketDto.getFlight())) != null)
            throw new IllegalArgumentException("There is already tickets with selected category");
        ticketRepo.saveAll(ticket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = getOne(id);
        ticketRepo.delete(ticket);
    }

    @Override
    public void edit(Long id, TicketEditDto ticket) {
        Ticket edited = ticketMapper.toEdit(ticket, getOne(id));
        checkMaxCapacity(ticket.getSeats(), edited.getFlight().getAirplane(), edited.getCategory());
        ticketRepo.save(edited);
    }

    @Override
    public List<Ticket> findByFlightAndCategory(Flight flight, Category category) {
        return ticketRepo.findByCategoryAndFlightOrderByIdAsc(category, flight);
    }

    private void checkMaxCapacity(int seats, Airplane airplane, Category category) {
        switch (category) {
            case ECONOMY -> {
                if (seats > airplane.getEconomic())
                    throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
            case BUSINESS -> {
                if (seats > airplane.getBusiness())
                    throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
            case FIRST -> {
                if (seats > airplane.getFirst())
                    throw new IllegalArgumentException("Number of seats can't be more than maximum capacity");
            }
        }
    }
}
