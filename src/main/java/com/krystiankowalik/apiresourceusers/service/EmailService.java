package com.krystiankowalik.apiresourceusers.service;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(EmailMessage mailMessage) throws Throwable;
}
