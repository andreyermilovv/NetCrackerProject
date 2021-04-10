package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight, Long> {
}
