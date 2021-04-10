package com.netcracker.airlines.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tickets")
public class TicketController {

    @GetMapping
    public String get(Model model){

        return "tickets";
    }
}
