package com.thinh.project.Shoptech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private Integer id;
	private String name;
	private String image;
	private String cStatus;
	private String note;

}
