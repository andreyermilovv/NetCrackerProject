package com.netcracker.airlines.mvc;

import com.netcracker.airlines.service.FlightService;
import com.netcracker.airlines.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("{flight}/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final FlightService flightService;

    @GetMapping
    public String get(@PathVariable Long flight,  Model model){
        model.addAttribute("tickets", ticketService.findByFlight(flightService.getOne(flight)));
        return "tickets";
    }
}
