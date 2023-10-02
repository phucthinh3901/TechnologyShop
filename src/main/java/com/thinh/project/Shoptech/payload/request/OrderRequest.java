package com.thinh.project.Shoptech.payload.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	private Integer _id;	
	
	private String status;
	
	private String address;
	
	private Double amount;
	
	private String phone;
	
	private Date createdAt;
	
	private String transactionId;
		
	private Integer user;
	
	private Date updatedAt;
	
	private List<allProduct> allProduct;

	
}
