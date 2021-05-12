package com.netcracker.airlines.service;

import com.netcracker.airlines.entities.Purchase;
import com.netcracker.airlines.entities.User;

import java.util.List;

public interface PurchaseService {

    void addPurchase(User user, Long flight, Long... id);

    List<Purchase> getByUser(User user);

    List<String> getFavourites(User user);
}
