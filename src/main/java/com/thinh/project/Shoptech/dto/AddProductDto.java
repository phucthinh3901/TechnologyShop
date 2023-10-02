package com.thinh.project.Shoptech.dto;

import java.util.List;

import com.thinh.project.Shoptech.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {

	private String name;

	private String description;

	private Double price;

	private Integer quantity;

	//private String image;

	private String offer;

	private String status;

	private List<Image> images;
}
