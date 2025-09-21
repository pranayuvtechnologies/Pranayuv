package com.Pran.Pran.Model;


import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class OTP {
    private String username;
    private int otp;

    public OTP() {

    }

    public OTP(String username, int otp) {
        this.username = username;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }
}
