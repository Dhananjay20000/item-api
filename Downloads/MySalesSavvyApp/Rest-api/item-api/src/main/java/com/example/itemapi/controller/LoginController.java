package com.example.itemapi.controller;

import com.example.itemapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://login-app-delta-rosy.vercel.app")
public class LoginController {
    @GetMapping("/test")
public String test() {
    return "Login Controller Working";
}
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
    
        // 🔐 SIMPLE HARD-CODED LOGIN
        if ("admin".equals(user.getUsername()) &&
            "1234".equals(user.getPassword())) {

            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
}