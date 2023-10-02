package com.thinh.project.Shoptech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinh.project.Shoptech.payload.request.LoginRequest;
import com.thinh.project.Shoptech.payload.request.SignupRequest;
import com.thinh.project.Shoptech.payload.request.TokenRefreshRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.payload.response.JwtResponse;
import com.thinh.project.Shoptech.payload.response.TokenRefreshResponse;
import com.thinh.project.Shoptech.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class AuthController {
	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		final JwtResponse result = userService.signIn(loginRequest);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@RequestBody TokenRefreshRequest request) {
		final TokenRefreshResponse result = userService.getRefreshtoken(request);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		final ApiResponse result = userService.signUp(signUpRequest);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
}
