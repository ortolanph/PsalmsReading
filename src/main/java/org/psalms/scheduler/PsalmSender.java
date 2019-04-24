package org.psalms.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PsalmSender {

    @Scheduled(cron = "0 0 8 * * *")
    public void sendDailyPsalm() {
        // gets the latest psalm number
        // retrieve it from bible service (NIV)
        // send it to subscribers
    }

}
