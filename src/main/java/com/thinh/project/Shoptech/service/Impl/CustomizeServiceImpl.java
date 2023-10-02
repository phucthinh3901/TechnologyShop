package com.thinh.project.Shoptech.service.Impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinh.project.Shoptech.entity.Customizes;
import com.thinh.project.Shoptech.payload.request.CustomizeIdRequest;
import com.thinh.project.Shoptech.payload.request.CustomizeRequest;
import com.thinh.project.Shoptech.payload.request.allDataRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.CategoryRepository;
import com.thinh.project.Shoptech.repository.CustomizeRepository;
import com.thinh.project.Shoptech.repository.OrderRepository;
import com.thinh.project.Shoptech.repository.ProductRepository;
import com.thinh.project.Shoptech.repository.UserRepository;
import com.thinh.project.Shoptech.service.CustomizeService;
import com.thinh.project.Shoptech.service.ImageService;

@Service
public class CustomizeServiceImpl implements CustomizeService {

	@Autowired
	CustomizeRepository customizeRepository;

	@Autowired
	ImageService imageService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;
//	
//	@Override
//	public List<SlideRequest> getSlideImage() {
//		List<SlideRequest> imageRequests = new ArrayList<>();
//		return imageRequests;
//	}

	@Override
	public ApiResponse deleteSlideImage(CustomizeIdRequest idRequest) {

		ApiResponse apiResponse = new ApiResponse();

		Customizes cus = customizeRepository.findById(idRequest.getCustomId()).orElse(null);

		customizeRepository.delete(cus);
		apiResponse.setMessage("Image deleted successfully");
		apiResponse.setSuccess(true);
		apiResponse.setData(cus);
		return apiResponse;
	}

	@Override
	public List<Customizes> getCustomizes() {
		return customizeRepository.findAll();
	}

	@Override
	public allDataRequest getAllData() {
		allDataRequest addData = new allDataRequest();
		addData.setAllCates(categoryRepository.findAll().size());
		addData.setAllProds(productRepository.findAll().size());
		addData.setAllOrders(orderRepository.findAll().size());
		addData.setAllUsers(userRepository.findAll().size());

		return addData;
	}

	
	
	@Override
	public CustomizeRequest CustomizesJson(String customizes, MultipartFile file) {
		CustomizeRequest Json = new CustomizeRequest();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Json = objectMapper.readValue(customizes, CustomizeRequest.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		return Json;
	}

	@Override
	public CustomizeRequest addCustomize(CustomizeRequest customizeRequest, MultipartFile file) {
		Customizes addCustomize = new Customizes();

		addCustomize.setFirstShow(customizeRequest.getFirstShow());
		addCustomize.setSlideImage(imageService.storeFile(file));
		addCustomize.setCreatedAt(new Date());
		addCustomize.setUpdatedAt(new Date());
		customizeRepository.save(addCustomize);

		CustomizeRequest request = new CustomizeRequest();
		request.setFirstShow(addCustomize.getFirstShow());
		request.setSlideImage(addCustomize.getSlideImage());
		request.setCreatedAt(addCustomize.getCreatedAt());
		request.setUpdatedAt(addCustomize.getUpdatedAt());

		return request;
	}

	@Override
	public ApiResponse upSlideImage(CustomizeRequest customizeRequest) {
		Customizes addCustomize = new Customizes();
		ApiResponse apiResponse = new ApiResponse();
		addCustomize.setFirstShow(customizeRequest.getFirstShow());
		addCustomize.setSlideImage(customizeRequest.getSlideImage());
		addCustomize.setCreatedAt(new Date());
		addCustomize.setUpdatedAt(new Date());
		customizeRepository.save(addCustomize);
		
		apiResponse.setData(addCustomize);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Upload slide image successed !!!");
		return apiResponse;
	}


}