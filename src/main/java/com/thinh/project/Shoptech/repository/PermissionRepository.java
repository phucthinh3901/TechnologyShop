package com.thinh.project.Shoptech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinh.project.Shoptech.entity.Epermission;
import com.thinh.project.Shoptech.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
	
	Optional<Permission> findByName(Epermission name);
}
