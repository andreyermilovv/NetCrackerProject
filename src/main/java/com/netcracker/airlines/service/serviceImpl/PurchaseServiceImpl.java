package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.entities.Purchase;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.exception.NoTicketsException;
import com.netcracker.airlines.exception.NotEnoughMoneyException;
import com.netcracker.airlines.repository.PurchaseRepo;
import com.netcracker.airlines.repository.TicketRepo;
import com.netcracker.airlines.repository.UserRepo;
import com.netcracker.airlines.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;

    private final TicketRepo ticketRepo;

    private final UserRepo userRepo;

    @Override
    public void addPurchase(Long id, User user, Integer amount) {
        Ticket ticket = ticketRepo.getOne(id);
        if (amount > ticket.getSeats()) throw new NoTicketsException(ticket.getFlight().getId());
        Integer cost = amount * ticket.getCost();
        if (cost > user.getMoney()) throw new NotEnoughMoneyException();
        Purchase purchase = new Purchase(ticket, user, amount, cost);
        user.setMoney(user.getMoney() - cost);
        ticket.setSeats(ticket.getSeats() - amount);
        purchaseRepo.save(purchase);
        ticketRepo.save(ticket);
        userRepo.save(user);
    }
}
