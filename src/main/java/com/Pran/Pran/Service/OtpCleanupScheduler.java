package com.Pran.Pran.Service;

import com.Pran.Pran.Repo.OtpRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OtpCleanupScheduler {

    private final OtpRepo otpRepo;

    public OtpCleanupScheduler(OtpRepo otpRepo) {
        this.otpRepo = otpRepo;
    }

    @Scheduled(fixedRate = 300000) // every 5 minutes
    public void cleanExpiredOtps() {
        otpRepo.deleteExpiredOtps();
    }
}
