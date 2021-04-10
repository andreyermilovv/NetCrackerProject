package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
