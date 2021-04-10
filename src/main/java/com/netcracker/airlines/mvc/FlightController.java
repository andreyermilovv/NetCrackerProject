package com.netcracker.airlines.mvc;

import com.netcracker.airlines.service.AirportService;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("flights")
public class FlightController {

    private final AirportService airportService;

    private final FlightService flightService;

    @GetMapping
    public String get(Model model){
        model.addAttribute("flights", flightService.getAll());
        model.addAttribute("airports", airportService.getAll());
        return "flights";
    }
}
