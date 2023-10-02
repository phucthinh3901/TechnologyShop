package com.thinh.project.Shoptech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.thinh.project.Shoptech.entity.RefreshToken;
import com.thinh.project.Shoptech.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);

	RefreshToken findByUserId(Integer userId);

	@Modifying
	int deleteByUser(User user);
}
