package com.thinh.project.Shoptech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thinh.project.Shoptech.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p where p.price < :productPrice and p.status = 'active'")
	List<Product> findProductByPrice(@Param("productPrice") Double price);
	
	@Query("SELECT p FROM Product p where p.status = 'active' and p.categories.cStatus = 'active'")
	List<Product> activeProduct();
		
}
