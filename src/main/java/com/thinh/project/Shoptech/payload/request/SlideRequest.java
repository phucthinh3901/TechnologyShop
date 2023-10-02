package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlideRequest {

	private Integer firstShow;
	
	private String _id;
	
	private String slideImage;
	
	private String createdAt;
	
	private String updatedAt;
	
	private Integer __v;
}
