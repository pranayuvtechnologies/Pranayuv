package com.Pran.Pran.Repo;


import com.Pran.Pran.Model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserCourseRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class UserCourseRowMapper implements RowMapper<UserCourse> {
        @Override
        public UserCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserCourse userCourse = new UserCourse();
            userCourse.setUser_course_id(rs.getInt("user_course_id"));
            userCourse.setUsername(rs.getString("username"));
            userCourse.setCourse_id(rs.getInt("course_id"));
            userCourse.setSelected_at(rs.getTimestamp("selected_at").toLocalDateTime());
            return userCourse;
        }
    }

    public UserCourse findById(int userCourseId) {
        String sql = "SELECT * FROM user_courses WHERE user_course_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserCourseRowMapper(), userCourseId);
    }

    public List<UserCourse> findByUsername(String username) {
        String sql = "SELECT * FROM user_courses WHERE username = ?";
        return jdbcTemplate.query(sql, new UserCourseRowMapper(), username);
    }

    public List<UserCourse> findByCourseId(int courseId) {
        String sql = "SELECT * FROM user_courses WHERE course_id = ?";
        return jdbcTemplate.query(sql, new UserCourseRowMapper(), courseId);
    }

    public void save(String username, int coid) {
        String sql = "INSERT INTO user_courses (username, course_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, coid);
    }

    public void delete(int userCourseId) {
        String sql = "DELETE FROM user_courses WHERE user_course_id = ?";
        jdbcTemplate.update(sql, userCourseId);
    }
    public void deleteByUsername(String username) {
        String sql = "DELETE FROM user_courses WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }



}