package com.thinh.project.Shoptech.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
	private String _id;
	private Integer user;
	private Date createdAt;
	private String rating;
	private String review;
}
