package org.psalms.services;

import org.psalms.beans.FullPsalm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String SEPARATOR_REGEX = ",";

    private static final String SUBSCRIBERS = "mail.sendto";

    @Autowired
    private Environment environment;

    public void sendPsalmToSubscribers(FullPsalm psalm) {
        String[] subscribers = environment.getProperty(SUBSCRIBERS).split(SEPARATOR_REGEX);

        for(String subscriber : subscribers) {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(subscriber);
            message.setSubject(String.format("Salmo %s", psalm.reference));
            message.setText(psalm.toString());

            mailSender.send(message);
        }
    }

}
