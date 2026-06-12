package com.example.todoapp.repository;
import com.example.todoapp.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
     public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM users",
            (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
            )
        );
    }
   
     public User findById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM users WHERE id = ?",
            new Object[]{id},
            (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
            )
        );
    }

     public void save(User user) {
        jdbcTemplate.update(
            "INSERT INTO users(name, email) VALUES(?, ?)",
            user.getName(),
            user.getEmail()
        );
    }


}
