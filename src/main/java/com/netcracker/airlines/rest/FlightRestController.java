package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flights")
public class FlightRestController {

    private final FlightService flightService;

    @PostMapping
    public void addAirport(@RequestBody FlightDto flightDto){
        flightService.save(flightDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        flightService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody FlightEditDto flightDto){
        flightService.edit(id, flightDto);
    }
}
