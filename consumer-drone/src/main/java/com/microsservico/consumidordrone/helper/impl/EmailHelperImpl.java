package com.microsservico.consumidordrone.helper.impl;

import com.microsservico.consumidordrone.helper.EmailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.microsservico.consumidordrone.consumer.DroneConsumer.msgEMail;
import static com.microsservico.consumidordrone.consumer.DroneConsumer.msgId;

@Component
public class EmailHelperImpl implements EmailHelper {

    @Value("${email.user}")
    private String emailToSend;

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailToSend);
        msg.setSubject("Alerta Drone ID: " + msgId);
        msg.setText(msgEMail);
        sender.send(msg);
    }
}
