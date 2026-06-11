package com.example.todoapp.model;

public class Todo {
 private Long id;
 private String title; 
 private boolean completed;
 
 public Todo(){

 }
 public Todo(Long id, String title, boolean completed){
     this.id = id;
     this.title = title;
     this.completed = completed;
 }

 public Long getId(){
    return id;
 }

 public String getTitle(){
    return title;
 }

 public void setId(long id){
    this.id = id;
 }

 public boolean isCompleted(){
    return completed;
 }

  public void setComplete(boolean completed){
    this.completed = completed;
  }
}
