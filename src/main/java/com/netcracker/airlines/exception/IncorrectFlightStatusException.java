package com.netcracker.airlines.exception;

import com.netcracker.airlines.entities.enums.Status;

public class IncorrectFlightStatusException extends RuntimeException {

    public IncorrectFlightStatusException(Status status) {
        super("Operation not supported for flight with status: " + status.name());
    }
}
