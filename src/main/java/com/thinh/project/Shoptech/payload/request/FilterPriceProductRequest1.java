package com.thinh.project.Shoptech.payload.request;

import java.util.List;

import com.thinh.project.Shoptech.dto.FeedbackDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterPriceProductRequest1 {
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String offer;
	private Integer sold;
	private Integer quantity;
	private String calculation_unit = "Cái";
	private String status;

	private String categories;
	private List<String> pimage;
	private List<FeedbackDto> feedbackDtos;
}
