package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airports")
public class AirportRestController {

    private final AirportService airportService;

    @PostMapping
    public void addAirport(@RequestBody @Valid AirportDto airportDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        airportService.save(airportDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        airportService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid AirportDto airportDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        airportService.edit(id, airportDto);
    }
}
