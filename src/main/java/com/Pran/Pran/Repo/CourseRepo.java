package com.Pran.Pran.Repo;

import com.Pran.Pran.Model.Course;
import com.Pran.Pran.Model.CourseIdCat;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CourseRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CourseIdCat> getcourseIdCat() {
        String sql = "SELECT course_id, category FROM courses";

        return jdbcTemplate.query(sql, new RowMapper<CourseIdCat>() {
            @Override
            public CourseIdCat mapRow(ResultSet rs, int rowNum) throws SQLException {
                CourseIdCat course = new CourseIdCat();
                course.setCourseId(rs.getInt("course_id"));
                course.setCategory(rs.getString("category"));
                return course;
            }
        });
    }


    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setCourseId(rs.getInt("course_id"));
            course.setCategory(rs.getString("category"));
            course.setDescription(rs.getString("description"));
            course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return course;
        }
    }

    public int addCourse(Course course) {
        String sql = "INSERT INTO courses ( category, description, created_at) VALUES ( ?, ?, ?)";
        return jdbcTemplate.update(sql,

                course.getCategory(),
                course.getDescription(),
                course.getCreatedAt() != null ? course.getCreatedAt() : LocalDateTime.now()
        );
    }


    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }


    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        return jdbcTemplate.queryForObject(sql, new CourseRowMapper(), courseId);
    }

    public int updateCourse(Course course) {
        String sql = "UPDATE courses SET category = ?, description = ? WHERE course_id = ?";
        return jdbcTemplate.update(sql,

                course.getCategory(),
                course.getDescription(),
                course.getCourseId()
        );
    }


    public int deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        return jdbcTemplate.update(sql, courseId);
    }

    public Integer findCourseIdWithCat(String cat) {
        String sql = "SELECT course_id FROM courses WHERE category = ?";
        List<Integer> courseIds = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt("course_id"), // Simplified RowMapper using lambda
                cat
        );

        if (!courseIds.isEmpty()) {
            return courseIds.get(0); // Return the first course_id if the list is not empty
        } else {
            return null; // Or throw an exception, or return a default value, depending on your requirements
        }
    }
}