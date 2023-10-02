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
public class ProductRequest {
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private String offer;
	private String status;
	private Integer CategoryId;
	private List<String> image;
	
}
