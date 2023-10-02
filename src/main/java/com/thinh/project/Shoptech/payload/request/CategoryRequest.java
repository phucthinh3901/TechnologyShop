package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryRequest {
	
	private Integer id;
	
	private String status;
	
	private String note;
	
}
