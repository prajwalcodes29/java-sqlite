package com.example.todoapp.controller;
import com.example.todoapp.dto.CreateTodoRequest;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.CsvParserService;
import com.example.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service){
        this.service = service;
    }

    @GetMapping
    public List<Todo> geTodos(){
        return service.getAllTodos();
    }

    @PostMapping
    public String createTodo(
        @RequestBody CreateTodoRequest request
    ){
        service.createTodo(request.getTitle());
        return "Todo Created";
    }

    @PostMapping("/upload")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {
        CsvParserService.parseAndSave(file);
        return "CSV upload route created";
    }
}
