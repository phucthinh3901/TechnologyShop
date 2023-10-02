package com.thinh.project.Shoptech.payload.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CustomizeRequest {
	
	private String slideImage;
	
	private Integer firstShow;
	
	private Date createdAt;
	
	private Date updatedAt;
}
