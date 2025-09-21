package com.Pran.Pran.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phone_number;
    @Column(name = "message")
    private String message;

    public Contact() {

    }

    public Contact(String email, String phone_number, String message) {
        this.email = email;
        this.phone_number = phone_number;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
