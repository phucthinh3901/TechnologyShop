package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedBackRequest {
	private Integer productId;
    private Integer userId;
    private String rating;
    private String review;
}
