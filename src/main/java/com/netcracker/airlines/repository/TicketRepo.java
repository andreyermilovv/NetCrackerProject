package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findByFlight(Flight flight);
}
