package com.evollo.fullstack.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cursoslwilker@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
        log.info("Email send");

    }
}
