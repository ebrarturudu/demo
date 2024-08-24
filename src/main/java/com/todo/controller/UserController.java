package com.todo.controller;

import com.todo.BaseEntity;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @GetMapping
    public BaseEntity.User createUser(@RequestBody BaseEntity.User user) {
        return userService.createUser(user);
    }

}
