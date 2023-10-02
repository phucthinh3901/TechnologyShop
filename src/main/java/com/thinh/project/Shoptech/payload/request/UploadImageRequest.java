package com.thinh.project.Shoptech.payload.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageRequest {

	private Integer firstShow = 0 ;
	
	private String slideImage;
	
	private Date createdAt;
	
	private Date updatedAt;
}
