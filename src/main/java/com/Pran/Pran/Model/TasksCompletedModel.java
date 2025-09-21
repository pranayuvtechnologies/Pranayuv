package com.Pran.Pran.Model;

import org.springframework.stereotype.Component;

@Component
public class TasksCompletedModel {
    private String username;
    private String linktogithub;

    public TasksCompletedModel(String username, String linktogithub) {
        this.username = username;
        this.linktogithub = linktogithub;
    }

    public TasksCompletedModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinktogithub() {
        return linktogithub;
    }

    public void setLinktogithub(String linktogithub) {
        this.linktogithub = linktogithub;
    }
}
