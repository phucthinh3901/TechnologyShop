package com.thinh.project.Shoptech.entity;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name="[order]")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer id;
		
	@Column(name="mount")
	private Double amount;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="transactionId")
	private String transactionId;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="createdAt")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="updatedAt")
	private Date updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.orders", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="status_id")
	private OrderStatus status;
	
}
