package com.thinh.project.Shoptech.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thinh.project.Shoptech.entity.Feedback;
import com.thinh.project.Shoptech.payload.request.DeleteFeedBackRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.FeedBackRepository;
import com.thinh.project.Shoptech.repository.ProductRepository;
import com.thinh.project.Shoptech.repository.UserRepository;
import com.thinh.project.Shoptech.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	FeedBackRepository feedBackRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public ApiResponse DeleteFeedBack(DeleteFeedBackRequest deleteFeedBackRequest) {
		ApiResponse apiResponse = new ApiResponse();
		Integer productId = deleteFeedBackRequest.getProductId();
		Integer userId = deleteFeedBackRequest.getUserId();
		
		if(productId == null || userId == null)
			 apiResponse.setMessage("All filled must be required");		
		else {
//			Product productOptional = productRepository.findById(productId).orElse(null);
//			User user = userRepository.findById(userId).orElse(null);	
			List<Feedback> feedback = feedBackRepository.findByProductIdAndUserId(productId, userId);
			if(feedback == null) {
				apiResponse.setSuccess(false);
				apiResponse.setMessage("All filled must be required");	
			}
			else {
				feedBackRepository.deleteAll(feedback);		
				apiResponse.setSuccess(true);
				apiResponse.setMessage("Your review is deleted");
			}
					
		}
		return apiResponse;
	}
	
	
}
