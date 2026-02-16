package com.jingran.carpool_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jingran.carpool_backend.dto.SigninRequest;
import com.jingran.carpool_backend.dto.SignupRequest;
import com.jingran.carpool_backend.entity.User;
import com.jingran.carpool_backend.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        // if (request.password.equals(request.confirmPassword)) {
        // return ResponseEntity.ok(Map.of("message", "signup ok v2"));
        // }

        // return ResponseEntity.badRequest().body(Map.of("error", "The passwords do not
        // match."));
        if (userRepository.existsByEmail(request.username)) {
            return ResponseEntity.badRequest().body(Map.of("error", "The email is already in use."));
        }
        User newUser = new User();
        newUser.setEmail(request.username);
        newUser.setUsername(request.name);
        newUser.setPassword(request.password);

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Signup successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SigninRequest request) {

        boolean userExists = userRepository.existsByEmail(request.username);
        if (!userExists) {
            return ResponseEntity.badRequest().body(Map.of("error", "User does not exist."));
        }

        List<User> users = userRepository.findByEmail(request.username).stream().toList();
        if (users.size() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "User does not exist."));
        } else if (users.size() > 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Multiple users with the same email exist."));
        } else {
            User user = users.get(0);
            if (!request.password.equals(user.getPassword())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Password is incorrect."));
            }

            return ResponseEntity.ok().body(Map.of("message", "Login successfully!"));
        }

        // String testUsername = "lily@umass.edu";
        // String testPassword = "12345678";
        // if (request.username.equals(testUsername) &&
        // request.password.equals(testPassword)) {
        // return ResponseEntity.ok(Map.of("message", "Login successfully!"));
        // }
        // return ResponseEntity.badRequest().body(Map.of("error", "Invalid username or
        // password."));
    }

    @GetMapping("/getname")
    public ResponseEntity<?> getName(@RequestHeader(value = "Authorization", required = false) String token) {
        String currentUser = RideController.getCurrentUserFromToken(token);
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(currentUser);
    }

}