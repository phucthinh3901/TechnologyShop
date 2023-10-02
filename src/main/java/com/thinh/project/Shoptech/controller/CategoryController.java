package com.thinh.project.Shoptech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.payload.request.AddCategoryRequest;
import com.thinh.project.Shoptech.payload.request.CategoryRequest;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.CategoryRepository;
import com.thinh.project.Shoptech.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private CategoryService categoryService;
	 
	@GetMapping("/all-category")
	  public List<Category> getAllCategories() {
		
	    return categoryRepository.activeCategory();
	    
	  }
	
	@GetMapping("/all-category1")
	  public List<Category> getAllCategories1() {
	    return categoryRepository.findAll();
	  }
	
//	@PostMapping("/add-category")
//	public ResponseEntity<Category> addCategory (@RequestBody CategoryRequest categoryRequest){
//		Category savedCategory = categoryService.addCategory(categoryRequest);
//	    return ResponseEntity.ok(savedCategory);
//	}
	
	@GetMapping("/getCategoryById/{id}")
	  public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
	    Optional<Category> optionalCategory = categoryRepository.findById(id);
	    if (optionalCategory.isPresent()) {
	      Category category = optionalCategory.get();
	      return ResponseEntity.ok(category);
	    } else {
	      return ResponseEntity.notFound().build();
	    }	
	  }
	
	

	@PostMapping("/edit-category")
	  public ApiResponse updateCategory(@RequestBody CategoryRequest categoryRequest) {
	    return categoryService.updateCategory(categoryRequest);
	  }

    @PostMapping("/delete-category")
    public ApiResponse deleteCategoryById(@RequestBody DeleteRequest id) {
       return categoryService.deleteCategoryById(id);
    }  
//	@PostMapping(value= "/add-category",consumes = {
//			MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE
//	})
//	public CategoryRequest addCategory(@RequestParam("category") String category, @RequestParam("file") MultipartFile file) {
//		
//		CategoryRequest CategoryJson = categoryService.CategetJson(category, file);
//		
//		CategoryRequest savedCategory = categoryService.addCategory(CategoryJson,file);
//		
//		//imageStorageService.saveCategoryImage(file);
//		return savedCategory;		
//	}
    @PostMapping("/add-category")
    public ApiResponse addCate(@RequestBody AddCategoryRequest addCategoryRequest) {
    	return categoryService.addCate(addCategoryRequest);
    }
    
	
}
