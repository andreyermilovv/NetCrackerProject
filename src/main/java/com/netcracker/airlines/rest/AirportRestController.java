package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airports")
public class AirportRestController {

    private final AirportService airportService;

    @PostMapping
    public void addAirport(@RequestBody AirportDto airportDto){
        airportService.save(airportDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        airportService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody AirportDto airportDto){
        airportService.edit(id, airportDto);
    }
}
