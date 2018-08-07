package com.method.userservice.controllers;

import javax.validation.Valid;

import com.method.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.method.userservice.bl.UserManager;
import com.method.userservice.entity.User;
import com.method.userservice.entity.VerificationCode;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserManager userManager;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@ModelAttribute @Valid User provided) throws Exception {
        User user = userManager.login(provided);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@ModelAttribute @Valid User provided) throws Exception {
        User user = userManager.register(provided);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/retrivePassword")
    public ResponseEntity<User> retrivePassword(@ModelAttribute @Valid User provided,
                                                @ModelAttribute @Valid VerificationCode verificationCode, @RequestParam String newPassword)
            throws Exception {
        User user = userManager.retrivePassword(provided, verificationCode, newPassword);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/verificationCode")
    public ResponseEntity<String> sendVerificationCode(@ModelAttribute @Valid User provided) throws Exception {
        String code = userManager.sendVerificationCode(provided);
        return ResponseEntity.status(HttpStatus.OK).body(code);
    }

    @PostMapping("/insert/test")
    public ResponseEntity<String> insertTest(@ModelAttribute @Valid User provided) throws Exception {
        userService.insert(provided);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
