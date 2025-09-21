package com.Pran.Pran;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PromotionalEmailScheduler {

    private final PromotionalMails promoService;

    public PromotionalEmailScheduler(PromotionalMails promoService) {
        this.promoService = promoService;
    }


    @Scheduled(cron = "0 16 22 * * ?")

    public void scheduleDailyPromo() {
        promoService.sendDailyPromoBatch(1);
    }
}
