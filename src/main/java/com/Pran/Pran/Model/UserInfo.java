package com.Pran.Pran.Model;

import org.springframework.stereotype.Component;


@Component
public class UserInfo {

    private int userId;
    private String fullName;
    private String username;
    private String emailPhone;
    private String category;
    private String workingHours;

    // Getters and Setters


    public UserInfo(int userId, String fullName, String username, String emailPhone, String category, String workingHours) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.emailPhone = emailPhone;
        this.category = category;
        this.workingHours = workingHours;
    }

    public UserInfo() {
    }

    public UserInfo(String fullName, String username, String emailPhone, String category, String workingHours) {
        this.fullName = fullName;
        this.username = username;
        this.emailPhone = emailPhone;
        this.category = category;
        this.workingHours = workingHours;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailPhone() {
        return emailPhone;
    }

    public void setEmailPhone(String emailPhone) {
        this.emailPhone = emailPhone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}

