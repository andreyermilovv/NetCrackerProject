package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepo extends JpaRepository<Flight, Long> {

    List<Flight> findByStatus(Status status);

    List<Flight> findByStatusNot(Status status);
}
