package com.netcracker.airlines.exception;

public class DuplicateUserEmailException extends RuntimeException{

    public DuplicateUserEmailException(String email) {
        super("User with email " + email + " already exists");
    }
}
