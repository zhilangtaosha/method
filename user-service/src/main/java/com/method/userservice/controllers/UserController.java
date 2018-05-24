package com.method.userservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.method.userservice.bl.UserManager;
import com.method.userservice.entity.User;
import com.method.userservice.entity.VerificationCode;
import com.method.userservice.util.Response;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserManager userManager;

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

}
