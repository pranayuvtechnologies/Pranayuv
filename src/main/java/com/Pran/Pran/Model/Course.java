package com.Pran.Pran.Model;



import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class Course {

    private int courseId;
    private String category;
    private String description;
    private LocalDateTime createdAt;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(int courseId, String category, String description, LocalDateTime createdAt) {
        this.courseId = courseId;
        this.category = category;
        this.description = description;
        this.createdAt = createdAt;
    }
    public Course(String category, String description, LocalDateTime createdAt) {

        this.category = category;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
