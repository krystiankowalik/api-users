package com.krystiankowalik.apiresourceusers.service;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmailServiceSpringBoot implements EmailService {


    @Value("${email.server.address}")
    private String BASE_URL;

    @Value("${email.server.endpoint}")
    private String ENDPOINT;

    @Override
    public void sendEmail(EmailMessage mailMessage) throws Throwable {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(BASE_URL + ENDPOINT, mailMessage, EmailMessage.class);
    }
}
