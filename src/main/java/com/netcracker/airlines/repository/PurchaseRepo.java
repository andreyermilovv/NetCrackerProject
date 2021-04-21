package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
}
