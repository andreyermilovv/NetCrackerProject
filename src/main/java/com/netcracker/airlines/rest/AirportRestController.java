package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.entities.Airport;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airports")
@PreAuthorize("hasRole('ADMIN')")
public class AirportRestController {

    private final AirportService airportService;

    @GetMapping("{id}")
    public AirportDto getAirport(@PathVariable Long id){
        Airport airport = airportService.getOne(id);
        AirportDto airportDto = new AirportDto();
        airportDto.setCity(airport.getCity());
        airportDto.setCountry(airport.getCountry());
        return airportDto;
    }

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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
