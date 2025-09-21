package com.Pran.Pran.Repo;


import com.Pran.Pran.Model.Course;
import com.Pran.Pran.Model.Task;
import com.Pran.Pran.Model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class TasksRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserCourseRepo userCourseRepo;

    @Autowired
    private CourseRepo courseRepo;

    // RowMapper to map a database row to a Task object
    private static class TaskRowMapper implements RowMapper<Task> {
        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            Task task = new Task();
            task.setTask_id(rs.getInt("task_id"));
            task.setCourse_id(rs.getInt("course_id"));
            task.setCategory(rs.getString("category"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setDue_date(rs.getTimestamp("due_date").toLocalDateTime());
            task.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            return task;
        }
    }

    public Task findById(int taskId) {
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        return jdbcTemplate.queryForObject(sql, new TaskRowMapper(), taskId);
    }

    public List<Task> findByCategory(String category) {
        String sql = "SELECT * FROM tasks WHERE category = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), category);
    }

    //    public List<Task> findByUsername(String username){
//        List<UserCourse> courses=  userCourseRepo.findByUsername(username);
//        int course_id = courses.get(0).getCourse_id();
//        Course c1 = courseRepo.getCourseById(course_id);
//        return findByCategory(c1.getCategory());
//    }
    public List<Task> findByUsername(String username) {
        List<UserCourse> courses=  userCourseRepo.findByUsername(username);

        // Check if any courses are associated with the username
        if (courses.isEmpty()) {
            System.out.println("No courses found for username: " + username);
            return Collections.emptyList(); // Return an empty list if no courses are found
        }

        int course_id = courses.get(0).getCourse_id();
        Course c1 = courseRepo.getCourseById(course_id);

        //Log category to ensure it is correct
        System.out.println("Category for course ID " + course_id + ": " + c1.getCategory());

        List<Task> tasks = findByCategory(c1.getCategory());

        // Log the tasks to see if any are found
        System.out.println("Tasks found for category " + c1.getCategory() + ": " + tasks);

        return tasks;
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (course_id, category, title, description, due_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getCourse_id(), task.getCategory(), task.getTitle(), task.getDescription(), task.getDue_date());
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET course_id = ?, category = ?, title = ?, description = ?, due_date = ? WHERE task_id = ?";
        jdbcTemplate.update(sql, task.getCourse_id(), task.getCategory(), task.getTitle(), task.getDescription(), task.getDue_date(), task.getTask_id());
    }

    public void delete(int taskId) {
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    public List<Task> getalltasksrepo(){
        String sql = "select * from tasks";
        return jdbcTemplate.query(sql,new TaskRowMapper());
    }
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM tasks WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId);
    }
    public void deleteTasksByCategory(String category) {
        String sql = "DELETE FROM tasks WHERE category = ?";
        jdbcTemplate.update(sql, category);
    }
}