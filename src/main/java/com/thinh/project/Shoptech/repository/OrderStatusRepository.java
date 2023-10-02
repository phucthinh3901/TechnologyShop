package com.thinh.project.Shoptech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinh.project.Shoptech.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{
	
}
