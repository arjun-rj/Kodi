package com.ourteam.kodi.controller;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.document.User;
import com.ourteam.kodi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public Object signupUser(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("/login")
    public Object loginUser(@RequestParam String phone) {
        return userService.loginUser(phone);
    }

    @GetMapping()
    public Object getUserById(@RequestParam String id, @RequestParam String token) {
        return userService.getUserById(id, token);
    }
}
