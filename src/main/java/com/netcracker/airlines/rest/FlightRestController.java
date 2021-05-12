package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.FlightDtoResponse;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.search.FlightSearchDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.mapper.FlightMapper;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flights")
//@PreAuthorize("hasRole('ADMIN')")
public class FlightRestController {

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    @GetMapping
    public List<Flight> flights(@RequestParam Status status) {
        if (status != null) return flightService.getByStatus(status);
        return flightService.getAll();
    }

    @GetMapping("filter")
    public List<FlightDtoResponse> filter(@RequestParam(required = false) String departureCountry,
                                          @RequestParam(required = false) String departureCity,
                                          @RequestParam(required = false) String destinationCountry,
                                          @RequestParam(required = false) String destinationCity,
                                          @RequestParam(required = false) LocalDate date,
                                          @RequestParam(required = false) Integer cost) {

        return flightService.search(flightMapper.toSearchDto(departureCountry, departureCity, destinationCountry, destinationCity, date, cost));
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid FlightEditDto flightDto, BindingResult errors) {
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        flightService.edit(id, flightDto);
    }


    @DeleteMapping("{id}")
    public void cancel(@PathVariable Long id) {
        flightService.cancel(id);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
