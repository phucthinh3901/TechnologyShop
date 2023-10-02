package com.thinh.project.Shoptech.service;

import com.thinh.project.Shoptech.payload.request.DeleteFeedBackRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;


public interface FeedBackService {

	ApiResponse DeleteFeedBack(DeleteFeedBackRequest deleteFeedBackRequest);

}
