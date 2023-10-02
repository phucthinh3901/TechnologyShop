package com.thinh.project.Shoptech.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.payload.request.AddCategoryRequest;
import com.thinh.project.Shoptech.payload.request.CategoryRequest;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.CategoryRepository;
import com.thinh.project.Shoptech.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ImageServiceImpl imageServiceImpl;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}

//	@Override
//	public CategoryRequest addCategory(CategoryRequest categoryRequest, MultipartFile file) {
//
//		Category addCategory = new Category();
//
//		addCategory.setName(categoryRequest.getName());
//		addCategory.setNote(categoryRequest.getNote());
//		addCategory.setCStatus(categoryRequest.getStatus());
//		addCategory.setImage(imageServiceImpl.storeFile(file));
// 
//		categoryRepository.save(addCategory);
//
//		CategoryRequest request = new CategoryRequest();
//		request.setName(addCategory.getName());
//		request.setNote(addCategory.getNote());
//		request.setStatus(addCategory.getCStatus());
//		request.setImage(addCategory.getImage());
//		
//		return request;
//	}

	@Override
	public ApiResponse deleteCategoryById(DeleteRequest req) {
		ApiResponse apiResponse = new ApiResponse();
		Category category = categoryRepository.findById(req.getId()).orElse(null);
		if (!category.getProducts().isEmpty()) {
			apiResponse.setMessage("Category have been existing!");
			apiResponse.setSuccess(false);
		} else {
			categoryRepository.delete(category);
			apiResponse.setMessage("Category deleted successfully");
			apiResponse.setSuccess(true);
			apiResponse.setData(category);
		}

		return apiResponse;
	}

	@Override
	public CategoryRequest CategetJson(String category, MultipartFile file) {

		CategoryRequest Json = new CategoryRequest();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Json = objectMapper.readValue(category, CategoryRequest.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		return Json;

	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public ApiResponse getProByCate(OrderIdRequest req) {

		Category category = categoryRepository.findById(req.getUserIdReq()).orElse(null);
		category.getProducts();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(category);
		return apiResponse;
	}

	@Override
	public ApiResponse addCate(AddCategoryRequest addCategoryRequest) {
		
		Category addCategory = new Category();

		ApiResponse apiResponse = new ApiResponse();
		addCategory.setName(addCategoryRequest.getName());
		addCategory.setNote(addCategoryRequest.getNote());
		addCategory.setCStatus(addCategoryRequest.getStatus());
		addCategory.setImage(addCategoryRequest.getImage());
		categoryRepository.save(addCategory);
		apiResponse.setData(addCategory);
		apiResponse.setSuccess(true);

		return apiResponse;
	}

	@Override
	public ApiResponse updateCategory(CategoryRequest categoryRequest) {
		ApiResponse apiResponse = new ApiResponse();
		Category category = categoryRepository.findById(categoryRequest.getId()).orElse(null);
		
		category.setNote(categoryRequest.getNote());
		category.setCStatus(categoryRequest.getStatus());
		
		categoryRepository.save(category);
		apiResponse.setData(category);
		apiResponse.setMessage("Update Category success!");
		apiResponse.setSuccess(true);
		return apiResponse;
	}

}
