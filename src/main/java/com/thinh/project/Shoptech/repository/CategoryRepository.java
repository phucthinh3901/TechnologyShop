package com.thinh.project.Shoptech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thinh.project.Shoptech.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT c FROM Category c where cStatus = 'active'")
	List<Category> activeCategory();
}
