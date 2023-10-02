package com.thinh.project.Shoptech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thinh.project.Shoptech.entity.Feedback;

public interface FeedBackRepository extends JpaRepository<Feedback, Integer>{
	@Query("SELECT fb FROM Feedback fb where fb.product.id = :productId and fb.id = :userID")
	List<Feedback> findByProductIdAndUserId(@Param("productId") Integer productId,@Param("userID") Integer userID);
}
