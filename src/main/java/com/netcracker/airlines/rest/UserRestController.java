package com.netcracker.airlines.rest;

import com.netcracker.airlines.dto.ProfileDto;
import com.netcracker.airlines.dto.UserDto;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.UserProfile;
import com.netcracker.airlines.exception.ValidExceptionHelper;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PutMapping("{user}")
    public void addProfileData(@PathVariable Long user,
                               @RequestBody @Valid ProfileDto profileDto,
                               BindingResult result){
        if (result.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(result));
        userService.addProfileData(user, profileDto);
    }

    @PostMapping("{user}")
    public void changeName(@PathVariable Long user,
                               @RequestBody @Valid UserDto userDto,
                               BindingResult result){
        if (result.hasErrors()) throw new IllegalArgumentException(ValidExceptionHelper.parseErrors(result));
        userService.edit(user, userDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
