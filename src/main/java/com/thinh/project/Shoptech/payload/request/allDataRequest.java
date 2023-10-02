package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class allDataRequest {
	
	private Integer allOrders;
	
	private Integer allCates;
	
	private Integer allProds;
	
	private Integer allUsers;
}
