package org.medtech.medmeet.authentication.controller;


import org.medtech.medmeet.authentication.domain.model.entity.User;
import org.medtech.medmeet.authentication.domain.service.UserService;
import org.medtech.medmeet.authentication.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody User user) {
        return ResponseEntity.ok(userService.signIn(user));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.ok("Registration successful");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // Add other methods as needed
}
