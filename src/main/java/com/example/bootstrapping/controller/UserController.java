package com.example.bootstrapping.controller;

import com.example.bootstrapping.model.User;
import com.example.bootstrapping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null ||
            user.getFirstName() == null || user.getLastName() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        if (repo.existsById(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        User saved = repo.save(user);
        saved.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/user/self")
    public ResponseEntity<?> getUser(Authentication auth) {
        User user = repo.findByEmail(auth.getName()).orElseThrow();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/self")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> updates, Authentication auth) {
        User user = repo.findByEmail(auth.getName()).orElseThrow();

        if (updates.containsKey("email") || updates.containsKey("accountCreated") || updates.containsKey("accountUpdated")) {
            return ResponseEntity.badRequest().body("Cannot update email/accountCreated/accountUpdated");
        }

        if (updates.containsKey("firstName")) user.setFirstName(updates.get("firstName"));
        if (updates.containsKey("lastName")) user.setLastName(updates.get("lastName"));
        if (updates.containsKey("password"))
            user.setPassword(encoder.encode(updates.get("password")));

        repo.save(user);
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
