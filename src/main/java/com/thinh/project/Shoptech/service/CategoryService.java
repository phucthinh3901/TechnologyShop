package com.thinh.project.Shoptech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.payload.request.AddCategoryRequest;
import com.thinh.project.Shoptech.payload.request.CategoryRequest;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;

public interface CategoryService {

	//CategoryRequest addCategory(CategoryRequest categoryRequest, MultipartFile file);

	List<Category> getAllCategories();

	Optional<Category> getCategoryById(Integer id);

	ApiResponse deleteCategoryById(DeleteRequest id);
	
	Optional<Category> findById(Integer id);

	CategoryRequest CategetJson(String category, MultipartFile file);
	
	ApiResponse getProByCate(OrderIdRequest req);
	
	ApiResponse addCate(AddCategoryRequest addCategoryRequest);
	
	ApiResponse updateCategory( CategoryRequest categoryRequest);
}
