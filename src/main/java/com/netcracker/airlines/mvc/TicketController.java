package com.netcracker.airlines.mvc;

import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Category;
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
@RequestMapping("flights/{flight}/tickets")
public class TicketController {

    private final FlightService flightService;

    private final TicketService ticketService;

    @GetMapping
    public String get(@PathVariable Long flight,  Model model){
        Flight current = flightService.getOne(flight);
        model.addAttribute("flight",current);
        model.addAttribute("first", ticketService.findByFlightAndCategory(current, Category.FIRST));
        model.addAttribute("business", ticketService.findByFlightAndCategory(current, Category.BUSINESS));
        model.addAttribute("economic", ticketService.findByFlightAndCategory(current, Category.ECONOMY));
        return "tickets";
    }
}
