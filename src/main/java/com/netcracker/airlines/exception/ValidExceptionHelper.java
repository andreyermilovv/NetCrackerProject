package com.netcracker.airlines.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidExceptionHelper {

    public static String parseErrors(BindingResult errors){
        StringBuilder result = new StringBuilder();
        if (errors.hasErrors()){
            result.append(errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }
        return result.toString();
    }
}
