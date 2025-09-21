package com.Pran.Pran.Repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class OtpRepo {


    private JdbcTemplate template;
    public OtpRepo(JdbcTemplate template){
        this.template = template;
    }


    public void savetoDb(String email, int otp) {
        String sqlUser = "INSERT INTO otppassword (username, otp, expiry_time) VALUES (?, ?, ?)";

        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
        template.update(sqlUser, email, otp, Timestamp.valueOf(expiryTime));
    }

//    public boolean checkOtp(String username, int otp, String password) {
//        String sqlQ = "SELECT otp FROM otppassword WHERE username = ?";
//
//        try {
//            Integer storedOtp = template.queryForObject(sqlQ, Integer.class, username);
////            System.out.println(storedOtp != null && storedOtp == otp);
//            return storedOtp != null && storedOtp == otp;
//        } catch (Exception e) {
//
//            return false;
//        }
//    }

public boolean checkOtp(String username, int otp, String password) {
    String sqlQ = "SELECT otp, expiry_time FROM otppassword WHERE username = ?";

    try {
        // optional cleanup
        return Boolean.TRUE.equals(template.queryForObject(sqlQ, (rs, rowNum) -> {
            int storedOtp = rs.getInt("otp");
            LocalDateTime expiryTime = rs.getTimestamp("expiry_time").toLocalDateTime();

            if (LocalDateTime.now().isAfter(expiryTime)) {
                deleteOtp(username); // optional cleanup
                return false;
            }

            return storedOtp == otp;
        }, username));
    } catch (Exception e) {
        return false;
    }
}

    private void deleteOtp(String username) {
        String sql = "delete from otppassword where username = ?";
        template.update(sql,username);
    }


    public void deleteExpiredOtps() {
        String sql = "DELETE FROM otppassword WHERE expiry_time < ?";
        template.update(sql, Timestamp.valueOf(LocalDateTime.now()));
    }


}
