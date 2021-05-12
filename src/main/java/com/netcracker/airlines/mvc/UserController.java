package com.netcracker.airlines.mvc;

import com.netcracker.airlines.entities.Purchase;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.service.PurchaseService;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final PurchaseService purchaseService;

    private final UserService userService;

    @GetMapping
    public String get(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("profile", userService.getUserProfile(user));
        List<Purchase> purchases = purchaseService.getByUser(user);
        model.addAttribute("purchases", purchases);
        model.addAttribute("allCost", purchases.stream().mapToInt(Purchase::getCost).sum());
        return "profile";
    }
}
