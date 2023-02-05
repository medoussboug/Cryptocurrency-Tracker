package com.example.cryptocurrencytracker.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }

    public String welcomeNotificationBodyFormatter(String username, String token, String expiryDate) {
        String bodyHtml = """
                        
                Hello %s
                Welcome to CryptoTracker, we inform you that your account has been successfuly created

                """;

        bodyHtml = String.format(bodyHtml, username, token, expiryDate);

        return bodyHtml;
    }

    public String priceReachedNotificationBodyFormatter(String username, String name) {
        String bodyHtml = """
                        
                Hello %s
                                
                Quick Quick!!!!!\\n The price of %s has gone to your desired price",

                """;
        bodyHtml = String.format(bodyHtml, username, name);
        return bodyHtml;
    }

    public String favoriteAddedNotificationBodyFormatter(String username, String name) {
        String bodyHtml = """
                        
                Hello %s
                                
                %s was successfully added to the favorites,

                """;
        bodyHtml = String.format(bodyHtml, username, name);
        return bodyHtml;
    }

}


