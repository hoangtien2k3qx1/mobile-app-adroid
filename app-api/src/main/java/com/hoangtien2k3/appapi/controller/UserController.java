package com.hoangtien2k3.appapi.controller;

import com.hoangtien2k3.appapi.model.User;
import com.hoangtien2k3.appapi.repository.UserRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")  // http://localhost:8081/users
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String userType,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        List<UserRepository.User> foundUser = userRepository.findByEmail(email);
        if (foundUser.size() > 0) {
            if (userType.equals("facebook")) {
                response.put("result", "ok");
                response.put("data", foundUser.get(0));
                response.put("message", "Registered user successfully");
                return (ResponseEntity<Map<String, Object>>) response;
            } else {
                response.put("result", "failed");
                response.put("data", "");
                response.put("message", "User already exists");
                return (ResponseEntity<Map<String, Object>>) response;
            }
        }

        User newUser = new User(name, email, userType, password);
        userRepository.save(newUser);

        response.put("result", "ok");
        response.put("data", newUser);
        response.put("message", "Registered user successfully");

        return ResponseEntity.ok(response);
    }
}
