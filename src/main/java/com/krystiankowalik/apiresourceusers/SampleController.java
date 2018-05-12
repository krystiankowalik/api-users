package com.krystiankowalik.apiresourceusers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SampleController {

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
}

