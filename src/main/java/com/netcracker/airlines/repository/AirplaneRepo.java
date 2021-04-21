package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {
}
