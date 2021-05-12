package com.netcracker.airlines.rest;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.service.PurchaseService;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase")
@RequiredArgsConstructor
public class PurchaseRestController {

    private final PurchaseService purchaseService;

    private final UserService userService;

    @PostMapping("{flight}")
    public void add(@RequestParam Long[] id,
                    @AuthenticationPrincipal User user,
                    @PathVariable Long flight) {
        purchaseService.addPurchase(user, flight, id);
    }

    @PutMapping
    public void addMoney(@AuthenticationPrincipal User user, @RequestParam Integer money){
        userService.addMoney(money, user);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
