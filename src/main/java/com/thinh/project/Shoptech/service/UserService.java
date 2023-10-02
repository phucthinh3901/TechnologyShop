package com.thinh.project.Shoptech.service;

import com.thinh.project.Shoptech.entity.User;
import com.thinh.project.Shoptech.payload.request.ChangePasswordReuqest;
import com.thinh.project.Shoptech.payload.request.LoginRequest;
import com.thinh.project.Shoptech.payload.request.SignupRequest;
import com.thinh.project.Shoptech.payload.request.TokenRefreshRequest;
import com.thinh.project.Shoptech.payload.request.UserIdRequest;
import com.thinh.project.Shoptech.payload.request.UserRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.payload.response.JwtResponse;
import com.thinh.project.Shoptech.payload.response.TokenRefreshResponse;

public interface UserService {

	public JwtResponse signIn(LoginRequest loginRequest);

	public ApiResponse signUp(SignupRequest signUpRequest);

	public TokenRefreshResponse getRefreshtoken(TokenRefreshRequest request);

	public ApiResponse changeInfo(UserRequest userRequest);

	public User getUserById(UserIdRequest id);

	public ApiResponse deleteUser(UserIdRequest req);

	public ApiResponse getAllUser();
	
	public ApiResponse changePassword(ChangePasswordReuqest changePasswordReuqest);

}
