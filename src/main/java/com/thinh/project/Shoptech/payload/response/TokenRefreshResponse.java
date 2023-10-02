package com.thinh.project.Shoptech.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshResponse {
	private String accessToken;

	private String refreshToken;
}
