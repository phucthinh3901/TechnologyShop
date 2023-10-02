package com.thinh.project.Shoptech.payload.request;

import java.util.List;

import com.thinh.project.Shoptech.dto.FeedbackDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {
	private Integer _id;
	private String pName;
	private String pDescription;
	private Double pPrice;
	private String pOffer;
	private Integer pSold = 0;
	private Integer pQuantity;
	private String calculation_unit = "Cái";
	private String pStatus;

	private Integer pCategory;
	private List<String> pImages;
	private List<FeedbackDto> pRatingsReviews;
	
//	private date createdAt;
}
