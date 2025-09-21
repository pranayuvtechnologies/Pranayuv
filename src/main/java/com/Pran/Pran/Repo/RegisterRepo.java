package com.Pran.Pran.Repo;


import com.Pran.Pran.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterRepo {


    private JdbcTemplate template;
    private PasswordEncoder encoder;
    @Autowired
    RegisterRepo(JdbcTemplate template,PasswordEncoder encoder){
        this.template=template;
        this.encoder=encoder;
    }

    public void addUserToDb(Users user) {
        String encodedPassword = encoder.encode(user.getPassword());

        String sqlUser = "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)";
        String sqlAuth = "INSERT INTO authorities (username, authority) VALUES (?, ?)";

        template.update(sqlUser, user.getUsername(), encodedPassword, 1);
        template.update(sqlAuth, user.getUsername(), "ROLE_USER");
    }

    public void addAdminToDb(Users user) {
        String encodedPassword = encoder.encode(user.getPassword());

        String sqlUser = "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)";
        String sqlAuth = "INSERT INTO authorities (username, authority) VALUES (?, ?)";

        template.update(sqlUser, user.getUsername(), encodedPassword, 1);
        template.update(sqlAuth, user.getUsername(), "ROLE_ADMIN");
    }
    public void changepass(String username, String password) {
        String encodedPassword = encoder.encode(password);
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        template.update(sql, encodedPassword, username);
    }

    public void deleteRecordFromOTP(String username) {
        String sql = "delete from otppassword where username=?";
        template.update(sql,username);
    }

    public List<String> getUsersToEmail(int limit) {
        String sql = """
        SELECT username FROM users u
        WHERE u.subscribed_to_promos = true
        AND NOT EXISTS (
                       SELECT 1 FROM promotional_emails pe
                       WHERE pe.user_email = u.username
                       AND pe.sent_date >= CURDATE() - INTERVAL 7 DAY
                     )
        LIMIT ?
    """;

        return template.queryForList(sql, String.class, limit);
    }

}
