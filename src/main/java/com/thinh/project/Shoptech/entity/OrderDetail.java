package com.thinh.project.Shoptech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {
	
	@EmbeddedId
	private OrderDetailId id;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "item_price")
	private Double price;

}
