package com.thinh.project.Shoptech.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "image")
	private String image;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Column(name = "created_at")
//	private Date registrationDate;

//	@Column(name = "createdAt")
//	private Date createdAt;
//	
//	@Column(name = "updatedAt")
//	private Date updatedAt;
	
	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id")
	private List<Permission> permission;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Feedback> feedback;


}
