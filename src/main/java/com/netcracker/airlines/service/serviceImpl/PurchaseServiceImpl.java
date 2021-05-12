package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.entities.*;
import com.netcracker.airlines.exception.NoTicketsException;
import com.netcracker.airlines.exception.NotEnoughMoneyException;
import com.netcracker.airlines.repository.*;
import com.netcracker.airlines.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;

    private final TicketRepo ticketRepo;

    private final FlightRepo flightRepo;

    private final UserProfileRepo userRepo;

    @Override
    public void addPurchase(User user, Long flightId, Long... id) {
        List<Ticket> tickets = ticketRepo.findAllById(Arrays.asList(id));
        if (tickets.stream().anyMatch(ticket -> !ticket.getAvailable())) throw new NoTicketsException(flightId);
        Integer cost = tickets.stream()
                .mapToInt(Ticket::getCost)
                .reduce(Integer::sum)
                .getAsInt();
        UserProfile userProfile = userRepo.findByUser(user);
        if (userProfile.getMoney() < cost) throw new NotEnoughMoneyException();
        Purchase purchase = new Purchase(tickets, user, cost);
        tickets.forEach(ticket -> ticket.setAvailable(false));
        userProfile.setMoney(userProfile.getMoney() - cost);
        purchaseRepo.save(purchase);
        ticketRepo.saveAll(tickets);
        userRepo.save(userProfile);
    }

    @Override
    public List<Purchase> getByUser(User user) {
        return purchaseRepo.findByUser(user);
    }

    @Override
    public List<String> getFavourites(User user) {
        List<Purchase> purchases = getByUser(user);
        return purchases.stream().
                map(x -> x.getTicket().get(0).getFlight().getDeparture().getCity()).
                collect(Collectors.toList());
    }
}
