package com.thinh.project.Shoptech.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private Integer _id;
	private String pName;
	private String pDescription;
	private Double pPrice;
	private String pOffer;
	private Integer pSold = 0;
	private Integer pQuantity;
	private String calculation_unit = "Cái";
	private String pStatus;
	private Double cartTotalCost;
	private Integer pCategory;
	private List<String> pImages;
	private List<FeedbackDto> pRatingsReviews;
}
