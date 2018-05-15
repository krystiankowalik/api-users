package com.krystiankowalik.apiresourceusers.controller;

import com.krystiankowalik.apiresourceusers.model.EmailMessage;
import com.krystiankowalik.apiresourceusers.network.ApiEmailService;
import com.krystiankowalik.apiresourceusers.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.security.Principal;

@RestController
public class SampleController {

    private final EmailService emailService;

    @Autowired
    public SampleController(@Qualifier(value = "emailServiceSpringBoot") EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/secure") //[1]
    public String secured() {
        return "Secured Hello World";
    }

    @GetMapping("/normal") //[1]
    public String normal() {
        return "Normal Hello World";
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;

    }


    @RequestMapping("/simpleemail")
    public ResponseEntity<EmailMessage> sendSimpleEmail(@RequestBody EmailMessage emailMessage) throws Throwable {

        EmailMessage simpleMailMessage =
                EmailMessage.builder()
                        .to(new String[]{"kryx07@gmail.com", "krystian.kowalik07@gmail.com"})
                        .subject("Debt Manager Account Activation")
                        .text("Yo,<br> <br> Click the link below to confirm registration: <br> <a href='http://google.com'>Google.com</a>")
                        .build();


        emailService.sendEmail(emailMessage);

        return new ResponseEntity<>(emailMessage, HttpStatus.OK);

    }


}

