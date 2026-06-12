package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.todoapp.dto.CreateUserRequest;  // ← error: can't find this class

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return service.getUserById(userId);
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        service.createUser(user);
        return "User Created successfully";

    }
}
