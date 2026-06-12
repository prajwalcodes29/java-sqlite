package com.example.todoapp.model;

public class Todo {
   private Long id;
   private String title;
   private boolean completed;
   private Long userId;

   public Todo() {

   }

   public Todo(Long id, String title, boolean completed, Long userId) {
      this.id = id;
      this.title = title;
      this.completed = completed;
      this.userId = userId;
   }

   public Long getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public void setId(long id) {
      this.id = id;
   }

   public boolean isCompleted() {
      return completed;
   }

   public void setComplete(boolean completed) {
      this.completed = completed;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
}
