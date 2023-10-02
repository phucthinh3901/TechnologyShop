package com.thinh.project.Shoptech.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "sold")
	private Integer sold;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "calculation_unit")
	private String calculation_unit = "Cái";

	@Column(name = "offer")
	private String offer;

	@Column(name = "status")
	private String status;

	@Column(name = "createdAt")
	private Date createdAt;
	
	@Column(name = "updatedAt")
	private Date updatedAt;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category categories;

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Image> images;

//	@Column(name = "image")
//	private String image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Feedback> feedback;

	
	@JsonIgnore
	@OneToMany(mappedBy = "id.products", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;

}
