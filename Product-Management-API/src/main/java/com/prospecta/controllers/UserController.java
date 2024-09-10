package com.prospecta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.dtos.UserDto;
import com.prospecta.model.User;
import com.prospecta.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register-user")
	public ResponseEntity<User> register(@Valid @RequestBody UserDto user) {

		User registeredCustomer = userService.registerUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
	}

}