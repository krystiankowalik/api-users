package com.krystiankowalik.apiresourceusers.controller;

import com.krystiankowalik.apiresourceusers.model.Authority;
import com.krystiankowalik.apiresourceusers.model.UserEntity;
import com.krystiankowalik.apiresourceusers.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(name = "/", params = "username")
    public ResponseEntity<UserEntity> getByUserName(@RequestParam String username) {
        val foundUser = userService
                .getUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user: " + username));
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

}
