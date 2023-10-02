package com.thinh.project.Shoptech.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customizes")
public class Customizes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private Integer id;
	
	@Column(name = "slideImage")
	private String slideImage;
	
	@Column(name = "firstShow")
	private Integer firstShow;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	@Column(name = "updatedAt")
	private Date updatedAt;
}
