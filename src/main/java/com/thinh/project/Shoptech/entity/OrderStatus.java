package com.thinh.project.Shoptech.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="order_status")
public class OrderStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	private Integer id;
	
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name="Status_Type")
	private EstatusOrder type;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Order> orders;
	
}

