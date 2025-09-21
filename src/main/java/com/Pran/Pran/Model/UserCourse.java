package com.Pran.Pran.Model;



import java.time.LocalDateTime;

public class UserCourse {
    private int user_course_id;
    private String username;
    private int course_id;
    private LocalDateTime selected_at;

    // Default constructor
    public UserCourse() {
    }

    // Constructor with all fields
    public UserCourse(int user_course_id, String username, int course_id, LocalDateTime selected_at) {
        this.user_course_id = user_course_id;
        this.username = username;
        this.course_id = course_id;
        this.selected_at = selected_at;
    }

    public UserCourse(String username, int course_id, LocalDateTime selected_at) {
        this.username = username;
        this.course_id = course_id;
        this.selected_at = selected_at;
    }

    // Getters and setters
    public int getUser_course_id() {
        return user_course_id;
    }

    public void setUser_course_id(int user_course_id) {
        this.user_course_id = user_course_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public LocalDateTime getSelected_at() {
        return selected_at;
    }

    public void setSelected_at(LocalDateTime selected_at) {
        this.selected_at = selected_at;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "UserCourse{" +
                "user_course_id=" + user_course_id +
                ", username='" + username + '\'' +
                ", course_id=" + course_id +
                ", selected_at=" + selected_at +
                '}';
    }
}