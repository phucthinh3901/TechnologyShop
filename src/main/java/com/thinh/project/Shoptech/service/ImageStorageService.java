package com.thinh.project.Shoptech.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
	public Boolean saveProductImage(List<MultipartFile> imageName, int productId);
	
	public Boolean saveCategoryImage(MultipartFile imageFiles);
}
