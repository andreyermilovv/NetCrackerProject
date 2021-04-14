package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flights")
public class FlightRestController {

    private final FlightService flightService;

    @GetMapping
    public List<Flight> flights(@RequestParam Status status){
        if (status != null) return flightService.getByStatus(status);
        return flightService.getAll();
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid FlightEditDto flightDto, BindingResult errors){
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
