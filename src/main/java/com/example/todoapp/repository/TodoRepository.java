package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Todo> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM todos",
                (rs, rowNum) -> new Todo(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBoolean("completed")));
    }

    public void save(String title) {
        jdbcTemplate.update(
                "INSERT INTO todos(title) VALUES(?)",
                title);
    }
}