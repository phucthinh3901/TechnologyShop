package com.thinh.project.Shoptech.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thinh.project.Shoptech.entity.Epermission;
import com.thinh.project.Shoptech.entity.Permission;
import com.thinh.project.Shoptech.entity.RefreshToken;
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
import com.thinh.project.Shoptech.repository.PermissionRepository;
import com.thinh.project.Shoptech.repository.UserRepository;
import com.thinh.project.Shoptech.security.jwt.JwtUtils;
import com.thinh.project.Shoptech.security.service.RefreshTokenService;
import com.thinh.project.Shoptech.security.service.UserDetailsImpl;
import com.thinh.project.Shoptech.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository usersRepository;

	@Autowired
	PermissionRepository rolesRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Override
	public JwtResponse signIn(LoginRequest loginRequest) {
		JwtResponse jwtResponse = new JwtResponse();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());

		RefreshToken refreshToken = refreshTokenService.getByUserId(userDetails.getId());
		if (refreshToken != null) {
			refreshTokenService.deleteByUserId(userDetails.getId());
		}
		refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		jwtResponse.setId(userDetails.getId());
		jwtResponse.setEmail(userDetails.getEmail());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setRefreshToken(refreshToken.getToken());
		jwtResponse.setToken(jwt);
		jwtResponse.setRole(roles.get(0));
		return jwtResponse;
	}

	@Override
	public TokenRefreshResponse getRefreshtoken(TokenRefreshRequest request) {
		TokenRefreshResponse refreshResponse = new TokenRefreshResponse();
		String requestRefreshToken = request.getRefreshToken();
		RefreshToken refreshToken = refreshTokenService.getByToken(requestRefreshToken).orElse(null);
		if (refreshToken != null) {
			refreshToken = refreshTokenService.verifyExpiration(refreshToken);
			User user = refreshToken.getUser();
			String token = jwtUtils.generateJwtToken(user.getUsername());
			refreshResponse.setAccessToken(token);
			refreshResponse.setRefreshToken(refreshToken.getToken());
		}
		return refreshResponse;
	}

	@Override
	public ApiResponse signUp(SignupRequest signUpRequest) {
		User user = new User();

		user.setEmail(signUpRequest.getEmail());
//		user.setAddress(signUpRequest.getAddress());
//		user.setPhone(signUpRequest.getPhone());
//		user.setImage(signUpRequest.getImage());
		user.setUsername(signUpRequest.getUserName());
//		user.setCreatedAt(new Date());
//		user.setUpdatedAt(new Date());

		user.setPassword(encoder.encode(signUpRequest.getPassword()));

//	List<String> strRoles = signUpRequest.getPermission();

		List<Permission> roles = new ArrayList<>();

		Permission userRole = rolesRepository.findByName(Epermission.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setPermission(roles);
		user.setStatus(true);
		usersRepository.save(user);
		return null;
	}

	@Override
	public ApiResponse changeInfo(UserRequest userRequest) {
		
		ApiResponse apiResponse = new ApiResponse();
		
		if(userRequest.getName() == null) {
			apiResponse.setMessage("Name can't null");
			return apiResponse;
		}
		
		User user = usersRepository.findById(userRequest.getUserId()).orElse(null);
		user.setUsername(userRequest.getName());
		user.setPhone(userRequest.getPhoneNumber());
		usersRepository.save(user);
	
		apiResponse.setMessage("User updated successfully");
		apiResponse.setSuccess(true);
		apiResponse.setData(user);

		return apiResponse;
	}

	@Override
	public User getUserById(UserIdRequest userId) {
		User user = usersRepository.findById(userId.getId()).orElse(null);
		return user;
	}

	@Override
	public ApiResponse deleteUser(UserIdRequest req) {
		ApiResponse apiResponse = new ApiResponse();
		User user = usersRepository.findById(req.getId()).orElse(null);
		if (user.getId() != null) {
			usersRepository.deleteById(req.getId());
			apiResponse.setMessage("User updated successfully");
			apiResponse.setSuccess(true);

		} else {
			apiResponse.setMessage("All filled must be required");
			apiResponse.setSuccess(true);

		}
		return apiResponse;
	}

	@Override
	public ApiResponse getAllUser() {
		ApiResponse apiResponse = new ApiResponse();

		List<User> users = usersRepository.findAll();
		apiResponse.setData(users);
		return apiResponse;
	}

	public ApiResponse changePassword(ChangePasswordReuqest changePasswordReuqest) {

		ApiResponse respone = new ApiResponse();

		User user = usersRepository.findById(changePasswordReuqest.getUserId()).get();
		if (!encoder.matches(changePasswordReuqest.getOldPassword(), user.getPassword())) {

			respone.setMessage("OldPassWord incorrect!");
			respone.setData(user);
			respone.setSuccess(false);
			return respone;

		}
		user.setPassword(encoder.encode(changePasswordReuqest.getNewPassword()));
		usersRepository.save(user);
		respone.setMessage("Change password success!");
		respone.setData(null);
		respone.setSuccess(true);
		return respone;
	}
}
