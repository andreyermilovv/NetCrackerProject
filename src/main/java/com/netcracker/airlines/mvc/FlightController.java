package com.netcracker.airlines.mvc;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.service.AirplaneService;
import com.netcracker.airlines.service.AirportService;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final AirplaneService airplaneService;

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal User user) {
        if (user == null || user.getRole().equals(Role.USER))
            model.addAttribute("flights", flightService.getByStatus(Status.UPCOMING));
        else model.addAttribute("flights", flightService.getWhenStatusNot(Status.TEMPLATE));
        model.addAttribute("airports", airportService.getAll());
        return "flights";
    }

    @GetMapping("templates")
    public String templates(Model model) {
        model.addAttribute("flights", flightService.getByStatus(Status.TEMPLATE));
        model.addAttribute("airports", airportService.getAll());
        model.addAttribute("airplanes", airplaneService.getAll());
        model.addAttribute(airplaneService.getAll());
        return "flightTemplates";
    }
}
