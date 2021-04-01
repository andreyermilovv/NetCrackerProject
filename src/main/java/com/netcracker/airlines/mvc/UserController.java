package com.netcracker.airlines.mvc;

import com.netcracker.airlines.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class UserController {


    @GetMapping
    public String get(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "profile";
    }
}
