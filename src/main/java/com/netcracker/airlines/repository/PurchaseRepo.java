package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Purchase;
import com.netcracker.airlines.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    List<Purchase> findByUser(User user);
}
