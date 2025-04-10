package net.hamidz.airTicketReservation.controller;

import net.hamidz.airTicketReservation.entity.User;
import net.hamidz.airTicketReservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String check() {
        return "Hello World!, 100% charged :)";
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.CREATED);
    }
}
