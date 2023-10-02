package com.thinh.project.Shoptech.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.entity.Image;
import com.thinh.project.Shoptech.entity.Product;
import com.thinh.project.Shoptech.repository.CategoryRepository;
import com.thinh.project.Shoptech.repository.ImageRepository;
import com.thinh.project.Shoptech.service.ImageStorageService;
import com.thinh.project.Shoptech.service.ProductService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImageServiceImpl imageServiceImpl;

	Logger logger = LoggerFactory.getLogger(ImageStorageServiceImpl.class);

	@Override
	public Boolean saveProductImage(List<MultipartFile> imageFiles, int productId) {
		try {
			Product product = productService.getProductById(productId);
			List<Image> imagesResult = new ArrayList<>();
			for (MultipartFile file : imageFiles) {
				Image image = new Image();
				String fileName = imageServiceImpl.storeFile(file);
				image.setUrl(fileName);
				image.setProduct(product);
				imagesResult.add(image);
			}
			imageRepository.saveAll(imagesResult);
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	@Override
	public Boolean saveCategoryImage(MultipartFile imageFiles) {
		try {
			Category cate = new Category();
		
			cate.setImage(imageServiceImpl.storeFile(imageFiles));
			categoryRepository.save(cate);
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}

}
