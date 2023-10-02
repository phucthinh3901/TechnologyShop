package com.thinh.project.Shoptech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.entity.Customizes;
import com.thinh.project.Shoptech.payload.request.CategoryRequest;
import com.thinh.project.Shoptech.payload.request.CustomizeIdRequest;
import com.thinh.project.Shoptech.payload.request.CustomizeRequest;
import com.thinh.project.Shoptech.payload.request.allDataRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.service.CustomizeService;

@RestController
@RequestMapping("/api/customize")
public class CustomizeController {

	@Autowired
	CustomizeService customizeService;

	@PostMapping("/delete-slide-image")
	public ApiResponse deleteSlideImage(@RequestBody CustomizeIdRequest id) {
		return customizeService.deleteSlideImage(id);
	}

	@GetMapping("/get-slide-image")
	public List<Customizes> getAllImage() {
		return customizeService.getCustomizes();
	}

	@GetMapping("/dashboard-data")
	public allDataRequest getAlldata() {
		return customizeService.getAllData();
	}
	
//	@PostMapping(value = "/upload-slide-image", consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.MULTIPART_FORM_DATA_VALUE })
//	public CustomizeRequest customizesUpload(@RequestParam("customize") String customizes,
//			@RequestParam("image") MultipartFile file) {
//
//		CustomizeRequest customizesJson = customizeService.CustomizesJson(customizes, file);
//
//		CustomizeRequest savedCustomize = customizeService.addCustomize(customizesJson, file);
//
//		// imageStorageService.saveCategoryImage(file);
//		return savedCustomize;
//	}
	@PostMapping("/upload-slide-image")
	public ApiResponse upSlideImage(@RequestBody CustomizeRequest customizeRequest) {
		return customizeService.upSlideImage(customizeRequest);
	}
	
}
