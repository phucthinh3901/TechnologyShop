package com.thinh.project.Shoptech.dto;

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
public class ProductDto {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Integer sold;
	
	private Integer quantity;
	
	private String calculation_unit;
	
	private String image;
	
	private String offer;
		
	private String status;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private List<FeedbackDto> feedbackDtos;
	
	private CategoryDto categories;
	
	private List<String> Pimage;
}
