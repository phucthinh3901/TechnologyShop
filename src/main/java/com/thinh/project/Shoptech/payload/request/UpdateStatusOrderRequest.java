package com.thinh.project.Shoptech.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusOrderRequest {

	private Integer orderIdRequest;
	private String orderStatus;
}

