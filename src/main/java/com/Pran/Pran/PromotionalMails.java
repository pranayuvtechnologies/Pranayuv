package com.Pran.Pran;
import java.util.*;

import com.Pran.Pran.Mail.MailClass;
import com.Pran.Pran.Mail.MailService;
import com.Pran.Pran.Repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PromotionalMails {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private RegisterRepo userRepo;

    @Autowired
    private MailService emailService;

    public void sendDailyPromoBatch(int batchSize) {
        List<String> emails = userRepo.getUsersToEmail(batchSize);

        for (String email : emails) {
            emailService.sendPromotionalEmail(
                    email,
                    "Your Exclusive Offer Awaits!"

            );

            savePromoLog(email);
        }
    }

    private void savePromoLog(String email) {
        String sql = "INSERT INTO promotional_emails (user_email, sent_date) VALUES (?, CURDATE())";
        template.update(sql, email);
    }

}
