package com.netcracker.airlines.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(DuplicateUserEmailException.class)
    public String registrationError(Model model){
        model.addAttribute("message", "Email isn't unique");
        return "registration";
    }
}
