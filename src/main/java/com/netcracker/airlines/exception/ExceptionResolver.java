package com.netcracker.airlines.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(DuplicateUserEmailException.class)
    public String registrationError(Model model, DuplicateUserEmailException exception, HttpServletRequest req){
        model.addAttribute("message", exception.getMessage());
        return req.getRequestURI();
    }
}
