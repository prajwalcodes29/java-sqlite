package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id);
    }

    public void createUser(User user) {
        repository.save(user);
    }

}
