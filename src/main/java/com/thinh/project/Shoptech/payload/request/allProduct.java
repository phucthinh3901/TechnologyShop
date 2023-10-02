package com.thinh.project.Shoptech.payload.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class allProduct {

	private Integer id;
	
	private Integer quantity;
	
	private String pname;

	private List<String> ImageProduct;

}
