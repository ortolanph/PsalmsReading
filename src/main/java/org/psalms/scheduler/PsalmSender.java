package org.psalms.scheduler;

import org.psalms.beans.FullPsalm;
import org.psalms.services.BibleAPIService;
import org.psalms.services.PsalmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PsalmSender {

    @Autowired
    private PsalmsService psalmsService;

    @Autowired
    private BibleAPIService bibleAPIService;

    //    @Scheduled(cron = "0 0 8 * * *")
    //@Scheduled(cron = "0 */2 * * * *")
    public void sendDailyPsalm() {
        int nextUnreadPsalm = psalmsService.nextUnreadPsalm();

        FullPsalm psalm = null;
        try {
            psalm = bibleAPIService.getPsalm(nextUnreadPsalm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // send it to subscribers (https://www.baeldung.com/spring-email)
        // https://www.baeldung.com/spring-mvc-with-velocity
        // https://www.baeldung.com/apache-velocity
    }

}
