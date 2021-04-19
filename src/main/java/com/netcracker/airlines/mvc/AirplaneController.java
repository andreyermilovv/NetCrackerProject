package com.netcracker.airlines.mvc;

import com.netcracker.airlines.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("airplanes")
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService airplaneService;

    @GetMapping
    public String get(Model model){
        model.addAttribute("airplanes", airplaneService.getAll());
        return "airplanes";
    }
}
