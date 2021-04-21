package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.AirplaneDto;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airplanes")
@PreAuthorize("hasRole('ADMIN')")
public class AirplaneRestController {

    private final AirplaneService airplaneService;

    @PostMapping
    public void addAirplane(@RequestBody @Valid AirplaneDto airplaneDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        airplaneService.save(airplaneDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        airplaneService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid AirplaneDto airplaneDto, BindingResult errors){
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        airplaneService.edit(id, airplaneDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
