package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.EditTemplateDto;
import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/templates")
@PreAuthorize("hasRole('ADMIN')")
public class TemplateRestController {

    private final FlightService flightService;

    @PostMapping
    public void addAirport(@RequestBody @Valid FlightTemplateDto flightTemplateDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        flightService.createTemplate(flightTemplateDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        flightService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid EditTemplateDto editTemplateDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        flightService.editTemplate(id, editTemplateDto);
    }

    @PostMapping("confirm")
    public void confirm(@Valid @RequestBody FlightDto flightDto, BindingResult errors) {
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        flightService.confirmFlight(flightDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
