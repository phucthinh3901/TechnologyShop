package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class findAllDataRequest {

	private Integer allCategories;
	
	private Integer allUser;
	
	private Integer allProduct;
	
	private Integer allOrder;
}
