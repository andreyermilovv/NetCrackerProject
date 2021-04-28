package com.netcracker.airlines.service;

import com.netcracker.airlines.entities.User;

public interface PurchaseService {

    void addPurchase(Long id, User user, Integer amount);
}
