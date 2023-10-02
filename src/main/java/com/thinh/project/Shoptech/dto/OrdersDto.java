package com.thinh.project.Shoptech.dto;

import java.util.Date;
import java.util.List;

import com.thinh.project.Shoptech.entity.User;
import com.thinh.project.Shoptech.payload.request.allProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
	private Integer _idOrder;	
	
	private String status;
	
	private String address;
	
	private Double amount;
	
	private String phone;
	
	private Date createdAt;
	
	private String transactionId;
		
	private UserDto user;
	
	private Date updatedAt;
	
	private List<allProduct> allProduct;
	
	
	
	
}
