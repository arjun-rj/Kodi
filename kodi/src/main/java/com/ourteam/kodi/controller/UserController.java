package com.ourteam.kodi.controller;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.document.User;
import com.ourteam.kodi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String phone) {
        return userService.loginUser(phone);
    }

    @GetMapping()
    public ResponseEntity<Object> getUserById(@RequestParam String id,
                                              @RequestHeader(name = "token", required = true) String token) {
        return userService.getUserById(id, token);
    }
}
