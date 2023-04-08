package com.example.smartthermostat.controller;

import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.model.dto.UserDto;
import com.example.smartthermostat.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public boolean createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public UserDto showUser(@AuthenticationPrincipal User user) {
        return userService.show(user);
    }
}
