package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepo extends JpaRepository<Airport, Long> {
}
