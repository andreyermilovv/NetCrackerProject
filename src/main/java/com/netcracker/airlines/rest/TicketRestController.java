package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.AirportDto;
import com.netcracker.airlines.dto.TicketDto;
import com.netcracker.airlines.dto.TicketEditDto;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    @PostMapping
    public void addAirport(@RequestBody @Valid TicketDto ticketDto, BindingResult errors) {
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        ticketService.save(ticketDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, BindingResult errors) {
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        ticketService.delete(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid TicketEditDto ticketEditDto, BindingResult errors) {
        if (errors.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(errors));
        ticketService.edit(id, ticketEditDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
