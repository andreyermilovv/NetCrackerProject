package com.netcracker.airlines.mvc;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String get(){
        return "registration";
    }

    @PostMapping
    public String post(@RequestParam String email,
                       @RequestParam String password,
                       @RequestParam String name,
                       @RequestParam String surname){
        User user = new User(name, surname, email, password);
        userService.save(user);
        return "redirect:/";
    }
}
