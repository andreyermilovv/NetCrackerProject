package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.EditTemplateDto;
import com.netcracker.airlines.dto.FlightDto;
import com.netcracker.airlines.dto.FlightTemplateDto;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/templates")
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
}
