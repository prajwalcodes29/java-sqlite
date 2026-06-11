package com.example.todoapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvParserService implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CsvParserService.context = applicationContext;
    }

    public static void parseAndSave(MultipartFile file) {
        if (context == null) {
            throw new IllegalStateException("Spring context not available");
        }

        JdbcTemplate jdbc = context.getBean(JdbcTemplate.class);
        int inserted = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = br.readLine();
            if (line == null) {
                System.out.println("CSV is empty");
                return; // empty file
            }

            boolean hasHeader = line.toLowerCase().contains("title");
            if (!hasHeader) {
                if (processAndInsert(line, jdbc)) inserted++;
            }

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (processAndInsert(line, jdbc)) inserted++;
            }

            System.out.println("Successfully parsed and stored " + inserted + " rows in DB.");

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV", e);
        }
    }

    private static boolean processAndInsert(String line, JdbcTemplate jdbc) {
        String[] cols = line.split(",", -1);
        if (cols.length == 0) return false;

        String title = cols[0].trim();
        if (title.isEmpty()) return false;

        boolean completed = false;
        if (cols.length > 1) {
            String v = cols[1].trim().toLowerCase();
            completed = v.equals("true") || v.equals("1") || v.equals("yes");
        }

        jdbc.update("INSERT INTO todos(title, completed) VALUES(?, ?)", title, completed ? 1 : 0);
        return true;
    }
}
