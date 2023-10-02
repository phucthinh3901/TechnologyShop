package com.thinh.project.Shoptech.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.entity.Customizes;
import com.thinh.project.Shoptech.payload.request.CustomizeIdRequest;
import com.thinh.project.Shoptech.payload.request.CustomizeRequest;
import com.thinh.project.Shoptech.payload.request.allDataRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;

public interface CustomizeService {

	//List<SlideRequest> getSlideImage();
	
	public ApiResponse deleteSlideImage(CustomizeIdRequest id);
	
	public List<Customizes> getCustomizes();
	
	public allDataRequest getAllData();
		
	public CustomizeRequest CustomizesJson(String customizes, MultipartFile file);
	
	public CustomizeRequest addCustomize(CustomizeRequest customizeRequest, MultipartFile file);
	
	public ApiResponse upSlideImage(CustomizeRequest customizeRequest);

}
