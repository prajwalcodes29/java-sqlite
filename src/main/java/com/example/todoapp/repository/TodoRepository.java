package com.example.todoapp.repository;

import com.example.todoapp.model.Todo;
import com.example.todoapp.model.User;

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
                (rs, rowNum) -> {
                    long userIdValue = rs.getLong("user_id");
                    Long userId = rs.wasNull() ? null : userIdValue;
                    return new Todo(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getBoolean("completed"),
                            userId);
                });
    }


    public List<Todo> findByUserId(Long userId) {
        return jdbcTemplate.query(
                "SELECT * FROM todos WHERE user_id = ?",
                new Object[]{userId},
                (rs, rowNum) -> {
                    long userIdValue = rs.getLong("user_id");
                    Long resolvedUserId = rs.wasNull() ? null : userIdValue;
                    return new Todo(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getBoolean("completed"),
                            resolvedUserId);
                });
    }

    public void save(String title, boolean completed, Long userId) {
        jdbcTemplate.update(
                "INSERT INTO todos(title, completed, user_id) VALUES(?, ?, ?)",
                title,
                completed ? 1 : 0,
                userId);
    }
}