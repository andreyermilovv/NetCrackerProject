package com.netcracker.airlines.exception;

import com.netcracker.airlines.entities.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectFlightStatusException extends RuntimeException {

    public IncorrectFlightStatusException(Status status) {
        super("Operation not supported for flight with status: " + status.name());
    }
}
