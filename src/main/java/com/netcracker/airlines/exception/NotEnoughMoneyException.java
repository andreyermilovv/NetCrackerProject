package com.netcracker.airlines.exception;

public class NotEnoughMoneyException extends RuntimeException{

    public NotEnoughMoneyException() {
        super("Not enough money for this flight");
    }
}
