package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightEditDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.enums.Status;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping
    public void addAirport(@RequestBody FlightTemplateDto flightTemplateDto){
        flightService.createTemplate(flightTemplateDto);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id){
        flightService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody FlightEditDto flightDto){
        flightService.edit(id, flightDto);
    }

    @PostMapping("confirm")
    public void confirm(@Valid @RequestBody FlightDto flightDto) {
        flightService.confirmFlight(flightDto);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        flightService.cancel(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
