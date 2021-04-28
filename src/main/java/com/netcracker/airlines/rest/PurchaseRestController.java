package com.netcracker.airlines.rest;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.service.PurchaseService;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase")
@RequiredArgsConstructor
public class PurchaseRestController {

    private final PurchaseService purchaseService;

    private final UserService userService;

    @PostMapping("{id}")
    public void add(@PathVariable Long id,
                    @AuthenticationPrincipal User user,
                    @RequestBody Integer amount) {
        purchaseService.addPurchase(id, user, amount);
    }

    @PutMapping
    public void addMoney(@AuthenticationPrincipal User user, @RequestParam Integer money){
        userService.addMoney(money, user);
    }
}
