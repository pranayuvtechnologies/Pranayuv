package com.Pran.Pran.Repo;


import com.Pran.Pran.Model.TasksCompletedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class TaskCompleted {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addTask(String username, String link) {
        String sql = "INSERT INTO completed_tasks (username, linktogithub) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, link);
    }

    public String findLinkByUsername(String username) {
        String sql = "SELECT linktogithub FROM completed_tasks WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, username);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null; // Or throw an exception if no link is found
        }
    }

    public void updateLink(String username, String newLink) {
        String sql = "UPDATE completed_tasks SET linktogithub = ? WHERE username = ?";
        jdbcTemplate.update(sql, newLink, username);
    }

    public void deleteByUsername(String username) {
        String sql = "DELETE FROM completed_tasks WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }
    public void truncateTable() {
        String sql = "TRUNCATE TABLE completed_tasks";
        jdbcTemplate.update(sql);
    }
    private static class TasksCompletedModelRowMapper implements RowMapper<TasksCompletedModel> {
        @Override
        public TasksCompletedModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            TasksCompletedModel task = new TasksCompletedModel();
            task.setUsername(rs.getString("username"));
            task.setLinktogithub(rs.getString("linktogithub"));
            // Map other fields if there are any
            return task;
        }
    }
    public List<TasksCompletedModel> getAllSubmits() {
        String sql = "SELECT * FROM completed_tasks";
        return jdbcTemplate.query(sql, new TasksCompletedModelRowMapper());
    }
    public void deleteSubmitsByUsername(String username) {
        String sql = "DELETE FROM completed_tasks WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }


}