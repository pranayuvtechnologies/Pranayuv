package com.Pran.Pran.Mail;

import com.Pran.Pran.Repo.OtpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private OtpRepo otpRepo;
    public void sendmail(MailClass mail) {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(mail.getSentTo());
            msg.setSubject(mail.getSubject());
            msg.setText(mail.getBody());
            msg.setFrom(mail.getSendFrom());

            sender.send(msg);

    }

    public void editPassword(String email) {
        MailClass mail = new MailClass();
        mail.setSentTo(email);
        mail.setSubject("change password");
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpRepo.savetoDb(email,otp);
        mail.setBody("this is your otp "+otp+" to change password");
        mail.setSendFrom("utubepre888@gmail.com");
        sendmail(mail);
    }

    public void sendPromotionalEmail(String email, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("");
        msg.setText(body);
        msg.setFrom("utubepre888@gmail.com");

        sender.send(msg);

    }
}
