package com.thinh.project.Shoptech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thinh.project.Shoptech.entity.Image;

import jakarta.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	@Modifying
	@Query("delete from Image i where i.product =: id")
	void deleteImage(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("delete from Image u where u.product = ?1 ")
    void deleteByMemId(int memId);
}
