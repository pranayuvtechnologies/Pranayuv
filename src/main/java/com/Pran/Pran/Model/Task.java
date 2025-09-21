package com.Pran.Pran.Model;


import java.time.LocalDateTime;

public class Task {
    private int task_id;
    private int course_id;
    private String category;
    private String title;
    private String description;
    private LocalDateTime due_date;
    private LocalDateTime created_at;

    // Default constructor
    public Task() {
    }

    public Task(int course_id, String category, String title, String description, LocalDateTime due_date, LocalDateTime created_at) {
        this.course_id = course_id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.created_at = created_at;
    }

    // Constructor with fields
    public Task(int task_id, int course_id, String category, String title, String description, LocalDateTime due_date, LocalDateTime created_at) {
        this.task_id = task_id;
        this.course_id = course_id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.created_at = created_at;
    }

    // Getters and setters for all fields
    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    // toString method for easy logging/debugging
    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", course_id=" + course_id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", due_date=" + due_date +
                ", created_at=" + created_at +
                '}';
    }
}