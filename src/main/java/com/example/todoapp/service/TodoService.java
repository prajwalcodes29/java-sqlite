package com.example.todoapp.service;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TodoService {
 
    private final TodoRepository repository;
    
    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    public List<Todo> getAllTodos(){
        return repository.findAll();
    }

    public List<Todo> getTodosByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void createTodo(String title, boolean completed, Long userId){
        repository.save(title, completed, userId);
    }
}