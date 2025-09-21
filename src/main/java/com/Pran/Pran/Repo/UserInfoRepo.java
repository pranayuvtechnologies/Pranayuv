package com.Pran.Pran.Repo;


import com.Pran.Pran.Model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserInfoRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserCourseRepo userCourseRepo;

    @Autowired
    private CourseRepo courseRepo;

    // RowMapper for mapping result set to UserInfo object
    private static class UserInfoRowMapper implements RowMapper<UserInfo> {
        @Override
        public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfo user = new UserInfo();
            user.setUserId(rs.getInt("user_id"));
            user.setFullName(rs.getString("full_name"));
            user.setUsername(rs.getString("username"));
            user.setEmailPhone(rs.getString("email_phone"));
            user.setCategory(rs.getString("category"));
            user.setWorkingHours(rs.getString("working_hours"));
            return user;
        }
    }


    public List<UserInfo> getAllUsers() {
        String sql = "SELECT * FROM user_info";
        return jdbcTemplate.query(sql, new UserInfoRowMapper());
    }




    public UserInfo getUserByUsername(String username) {
        String sql = "SELECT * FROM user_info WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new UserInfoRowMapper(), username);
    }

    public int addUser(UserInfo user) {
        userCourseRepo.save(user.getUsername(),courseRepo.findCourseIdWithCat(user.getCategory()));

        String sql = "INSERT INTO user_info (full_name, username, email_phone, category, working_hours) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getFullName(),
                user.getUsername(),
                user.getEmailPhone(),
                user.getCategory(),
                user.getWorkingHours()
        );
    }
    public void deleteUser(String username) {
        String sql = "DELETE FROM user_info WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }
    public List<UserInfo> getUsersByCategory(String category) {
        String sql = "SELECT * FROM user_info WHERE category = ?";
        return jdbcTemplate.query(sql, new UserInfoRowMapper(), category);
    }
}
