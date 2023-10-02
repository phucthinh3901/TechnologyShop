package com.thinh.project.Shoptech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinh.project.Shoptech.entity.User;
import com.thinh.project.Shoptech.payload.request.ChangePasswordReuqest;
import com.thinh.project.Shoptech.payload.request.UserIdRequest;
import com.thinh.project.Shoptech.payload.request.UserRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/edit-user")
	public ApiResponse changeInfo(@RequestBody UserRequest userRequest) {
		return userService.changeInfo(userRequest);
	}

	@PostMapping("/change-password")
	public ApiResponse changePassword(@RequestBody ChangePasswordReuqest changePassUser) {
		return userService.changePassword(changePassUser);
	}

	@PostMapping("/signle-user")
	public User getUserById(@RequestBody UserIdRequest id) {
		return userService.getUserById(id);
	}

	@PostMapping("/delete-user")
	public ApiResponse deleteUser(@RequestBody UserIdRequest req) {
		return userService.deleteUser(req);
	}

	@GetMapping("/all-user")
	public ApiResponse getAllUser() {
		return userService.getAllUser();
	}

}