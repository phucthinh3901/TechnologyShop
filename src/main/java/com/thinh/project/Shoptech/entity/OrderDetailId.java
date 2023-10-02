package com.thinh.project.Shoptech.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
public class OrderDetailId implements Serializable{

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id",nullable = false)
	private Order orders;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id",nullable = false)
	private Product products;
}
