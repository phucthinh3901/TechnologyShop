package com.thinh.project.Shoptech.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinh.project.Shoptech.dto.CartDto;
import com.thinh.project.Shoptech.dto.CategoryDto;
import com.thinh.project.Shoptech.dto.FeedbackDto;
import com.thinh.project.Shoptech.dto.ProductDto;
import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.entity.Feedback;
import com.thinh.project.Shoptech.entity.Image;
import com.thinh.project.Shoptech.entity.Product;
import com.thinh.project.Shoptech.entity.User;
import com.thinh.project.Shoptech.payload.request.AddFeedBackRequest;
import com.thinh.project.Shoptech.payload.request.DeleteFeedBackRequest;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.FilterPriceProductRequest;
import com.thinh.project.Shoptech.payload.request.FilterPriceProductRequest1;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.request.ProductByCategory;
import com.thinh.project.Shoptech.payload.request.ProductIdRequest;
import com.thinh.project.Shoptech.payload.request.ProductPriceRequest;
import com.thinh.project.Shoptech.payload.request.ProductRequest;
import com.thinh.project.Shoptech.payload.request.SingleProductRequest;
import com.thinh.project.Shoptech.payload.request.WishListDto;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.FeedBackRepository;
import com.thinh.project.Shoptech.repository.ProductRepository;
import com.thinh.project.Shoptech.repository.UserRepository;
import com.thinh.project.Shoptech.service.FeedBackService;
import com.thinh.project.Shoptech.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeedBackRepository feedBackRepository;

	@Autowired
	private FeedBackService feedBackService;

	@GetMapping("/all-product")
	public List<ProductDto> getAllProduct() {
		List<Product> products = productRepository.activeProduct();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();

		for (Product product : products) {
			CategoryDto categoryDto = new CategoryDto();

			Category category = product.getCategories();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());

			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setDescription(product.getDescription());
			productDto.setPrice(product.getPrice());
			productDto.setSold(product.getSold());
			productDto.setQuantity(product.getQuantity());
			productDto.setCalculation_unit(product.getCalculation_unit());
			productDto.setCreatedAt(product.getCreatedAt());
			productDto.setUpdatedAt(product.getUpdatedAt());

//			productDto.setImage(product.getImage());
			productDto.setOffer(product.getOffer());
			productDto.setStatus(product.getStatus());
			productDto.setCategories(categoryDto);

			List<FeedbackDto> feedbackDtos = new ArrayList<FeedbackDto>();
//				 for (Feedback feedback : feedbacks) {
//			            FeedbackDto feedbackDto = new FeedbackDto();
//			            feedbackDto.setId(feedback.getId());
//			            feedbackDto.setDate(feedback.getDate());
//			            feedbackDto.setComment(feedback.getComment());
//			            feedbackDto.setUser(feedback.getUser());
//			            feedbackDto.setVote(feedback.getVote());
//			            
//			            feedbackDtos.add(feedbackDto);
//			        }
			List<Image> img = product.getImages();
			List<String> imgages = new ArrayList<String>();

			for (Image item : img) {
				String url = item.getUrl();
				imgages.add(url);
			}
			productDto.setPimage(imgages);
			productDto.setFeedbackDtos(feedbackDtos);

			productDtos.add(productDto);
		}
		return productDtos;
	}

	@GetMapping("/all-product1")
	public List<ProductDto> getAllProduct1() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();

		for (Product product : products) {
			CategoryDto categoryDto = new CategoryDto();

			Category category = product.getCategories();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());

			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setDescription(product.getDescription());
			productDto.setPrice(product.getPrice());
			productDto.setSold(product.getSold());
			productDto.setQuantity(product.getQuantity());
			productDto.setCalculation_unit(product.getCalculation_unit());
			productDto.setCreatedAt(product.getCreatedAt());
			productDto.setUpdatedAt(product.getUpdatedAt());

//			productDto.setImage(product.getImage());
			productDto.setOffer(product.getOffer());
			productDto.setStatus(product.getStatus());
			productDto.setCategories(categoryDto);

			List<FeedbackDto> feedbackDtos = new ArrayList<FeedbackDto>();
//				 for (Feedback feedback : feedbacks) {
//			            FeedbackDto feedbackDto = new FeedbackDto();
//			            feedbackDto.setId(feedback.getId());
//			            feedbackDto.setDate(feedback.getDate());
//			            feedbackDto.setComment(feedback.getComment());
//			            feedbackDto.setUser(feedback.getUser());
//			            feedbackDto.setVote(feedback.getVote());
//			            
//			            feedbackDtos.add(feedbackDto);
//			        }
			List<Image> img = product.getImages();
			List<String> imgages = new ArrayList<String>();

			for (Image item : img) {
				String url = item.getUrl();
				imgages.add(url);
			}
			productDto.setPimage(imgages);
			productDto.setFeedbackDtos(feedbackDtos);

			productDtos.add(productDto);
		}
		return productDtos;
	}
//	@PostMapping("/add-product")
//	public ResponseEntity<Product> addProduct (@RequestBody ProductRequest categoryRequest){
//		Product savedProduct = productService.addProduct(categoryRequest);
//	    return ResponseEntity.ok(savedProduct);
//	}
	@PostMapping("/single-product")
	public SingleProductRequest getProductByIdproduct(@RequestBody OrderIdRequest req) {
		return productService.getProductByProductId(req);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Optional<Product> optionalCategory = productRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Product product = optionalCategory.get();
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/delete-product")
	public ApiResponse deleteProductById(@RequestBody DeleteRequest req) {
		return productService.deleteProductById(req);

	}
//
//	@PostMapping(value = "/add-product", consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ProductRequest addProduct(@RequestParam("product") String product,
//			@RequestParam("files") List<MultipartFile> file) {
//		ProductRequest productJson = productService.getJson(product, file);
//		Product savedProduct = productService.addProduct(productJson);
//		imageStorageService.saveProductImage(file, savedProduct.getId());
//		return productJson;
//	}

	@PostMapping("/add-product")
	public ApiResponse addProduct(@RequestBody ProductRequest productRequest) {
		return productService.addProduct(productRequest);
	}

	@PostMapping("/edit-product")
	public ApiResponse updateProduct(@RequestBody ProductRequest productRequest) {
		return productService.updateProduct(productRequest);
	}

//	@PostMapping(value = "/edit-product", consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ProductRequest updateProduct(@PathVariable("id") Integer id, @RequestParam("product") String product,
//			@RequestParam("files") List<MultipartFile> file) {
//		ProductRequest productJson = productService.getJson(product, file);
//		Product savedProduct = productService.UpdateProduct(id, productJson);
//		imageStorageService.saveProductImage(file, savedProduct.getId());
//		return productJson;
//	}

	@PostMapping("/wish-product")
	public ResponseEntity<List<WishListDto>> addProductsToWishlist(@RequestBody ProductIdRequest ProductIdRequest) {
		try {

			List<WishListDto> wishListItems = new ArrayList<>();
			for (Integer productId : ProductIdRequest.getProductArray()) {
				Product product = productService.getProductById(productId);
				WishListDto wishlistItem = new WishListDto();
				Category category = new Category();
				product.setCategories(category);

				wishlistItem.set_id(product.getId());
				wishlistItem.setPName(product.getName());
				wishlistItem.setPDescription(product.getDescription());
				wishlistItem.setPPrice(product.getPrice());
				wishlistItem.setPOffer(product.getOffer());
				wishlistItem.setPSold(product.getSold());
				wishlistItem.setPQuantity(product.getQuantity());
				wishlistItem.setCalculation_unit("Cái");
				wishlistItem.setPStatus(product.getStatus());

				wishlistItem.setPCategory(category.getId());

				List<Image> img = product.getImages();

				List<String> imgages = new ArrayList<String>();

				for (Image item : img) {
					String url = item.getUrl();
					imgages.add(url);
				}

				wishlistItem.setPImages(imgages);

				List<Feedback> feedbacks = product.getFeedback();
				List<FeedbackDto> result = new ArrayList<>();
				FeedbackDto dto = null;
				for (Feedback item : feedbacks) {
					dto = new FeedbackDto();
					dto.set_id(item.getUser().getUsername());
					dto.setCreatedAt(new Date());
					dto.setRating(item.getVote());
					dto.setReview(item.getComment());
					dto.setUser(item.getUser().getId());
					result.add(dto);
				}

				wishlistItem.setPRatingsReviews(result);

				wishListItems.add(wishlistItem);
			}
			return ResponseEntity.ok(wishListItems);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/cart-product")
	public ResponseEntity<List<CartDto>> addProductsToCart(@RequestBody ProductIdRequest productIds) {

		try {
			Double totolPrice = 0D;
			Category category = new Category();
			List<CartDto> cartItems = new ArrayList<>();
			for (Integer productId : productIds.getProductArray()) {
				Product product = productService.getProductById(productId);
				CartDto cartItem = new CartDto();

				product.setCategories(product.getCategories());

				cartItem.set_id(product.getId());
				cartItem.setPName(product.getName());
				cartItem.setPDescription(product.getDescription());
				cartItem.setPPrice(product.getPrice());
				cartItem.setPOffer(product.getOffer());
				cartItem.setPSold(product.getSold());
				cartItem.setPQuantity(product.getQuantity());
				cartItem.setCalculation_unit("Cái");
				cartItem.setPStatus(product.getStatus());
				cartItem.setPCategory(product.getCategories().getId());

				totolPrice += cartItem.getPPrice() * cartItem.getPQuantity();
				cartItem.setCartTotalCost(totolPrice);
				
				List<Image> img = product.getImages();
				List<String> imgages = new ArrayList<String>();
				for (Image item : img) {
					String url = item.getUrl();
					imgages.add(url);
				}

				cartItem.setPImages(imgages);

				List<Feedback> feedbacks = product.getFeedback();
				List<FeedbackDto> result = new ArrayList<>();
				FeedbackDto dto = null;
				for (Feedback item : feedbacks) {
					dto = new FeedbackDto();
					dto.set_id(item.getUser().getUsername());
					dto.setCreatedAt(new Date());
					dto.setRating(item.getVote());
					dto.setReview(item.getComment());
					dto.setUser(item.getUser().getId());
					result.add(dto);
				}

				cartItem.setPRatingsReviews(result);
				cartItems.add(cartItem);
			}
			return ResponseEntity.ok(cartItems);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/product-by-category")
	public List<ProductByCategory> getProductByCategory(@RequestBody OrderIdRequest req) {
		return productService.getProductByCategory(req);
	}

	@PostMapping("/add-review")
	public ApiResponse postAddReview(@RequestBody AddFeedBackRequest addFeedBackRequest) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			// Check if all required fields are present
			if (addFeedBackRequest.getProductId() == null || addFeedBackRequest.getProductId() == null
					|| addFeedBackRequest.getRating() == null || addFeedBackRequest.getReview() == null) {
				apiResponse.setMessage("All fields are required.");
				apiResponse.setSuccess(false);
			}
			// Retrieve the product with the given ID

			Product productOptional = productRepository.findById(addFeedBackRequest.getProductId()).orElse(null);
			if (productOptional == null) {
				apiResponse.setMessage("Cann't find product!");
				apiResponse.setSuccess(false);
			}

			User user = userRepository.findById(addFeedBackRequest.getUserId()).orElse(null);

			if (feedBackRepository
					.findByProductIdAndUserId(addFeedBackRequest.getProductId(), addFeedBackRequest.getUserId())
					.isEmpty()) {
				Feedback feedback = new Feedback();

				feedback.setProduct(productOptional);

				feedback.setComment(addFeedBackRequest.getReview());

				feedback.setUser(user);

				feedback.setVote(addFeedBackRequest.getRating());

				feedback.setDate(new Date());

				productOptional.getFeedback().add(feedback);

				productRepository.save(productOptional);

				feedBackRepository.save(feedback);

				apiResponse.setMessage("Review added successfully");
				apiResponse.setSuccess(true);
				apiResponse.setData(feedback);
			} else {
				apiResponse.setMessage("Your already reviewd the product");
				apiResponse.setSuccess(false);
			}
			// Create a new review object and add it to the product's list of reviews

		} catch (Exception e) {
			apiResponse.setMessage("Failed!");

		}
		return apiResponse;
	}

	@PostMapping("/delete-review")
	public ApiResponse deleteReview(@RequestBody DeleteFeedBackRequest deleteFeedBackRequest) {
		return feedBackService.DeleteFeedBack(deleteFeedBackRequest);
	}

	@PostMapping("/product-by-price")
	public ResponseEntity<List<FilterPriceProductRequest1>> filterProductByPrice(
			@RequestBody FilterPriceProductRequest filter) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.FilterProductByPrice(filter));
	}

}
