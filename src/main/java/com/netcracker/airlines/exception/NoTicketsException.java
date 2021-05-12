package com.netcracker.airlines.exception;

public class NoTicketsException extends RuntimeException {

    public NoTicketsException(Long id){
        super("There are no available tickets for this flight: " + id);
    }
}
