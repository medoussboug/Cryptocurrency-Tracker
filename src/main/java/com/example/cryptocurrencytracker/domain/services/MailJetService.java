package com.example.cryptocurrencytracker.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mBougueddach
 */
@Service
public class MailJetService {

    private final RestTemplate restTemplate;

    @Value("${mailjet.api.key.public}")
    private String apiKeyPublic;

    @Value("${mailjet.api.key.private}")
    private String apiKeyPrivate;

    @Autowired
    public MailJetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(String to, String subject, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(apiKeyPublic, apiKeyPrivate);

        Map<String, Object> payload = new HashMap<>();
        payload.put("Messages", Collections.singletonList(
                Map.of("From", Map.of("Email", "mob.dev2022@gmail.com", "Name", "Sender"),
                        "To", Collections.singletonList(Map.of("Email", to)),
                        "Subject", subject,
                        "TextPart", body)));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        String url = "https://api.mailjet.com/v3/send";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to send email: " + response.getBody());
        }
    }

}

