package com.thinh.project.Shoptech.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinh.project.Shoptech.dto.FeedbackDto;
import com.thinh.project.Shoptech.dto.ProductDto;
import com.thinh.project.Shoptech.entity.Category;
import com.thinh.project.Shoptech.entity.Feedback;
import com.thinh.project.Shoptech.entity.Image;
import com.thinh.project.Shoptech.entity.Product;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.FilterPriceProductRequest;
import com.thinh.project.Shoptech.payload.request.FilterPriceProductRequest1;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.request.ProductByCategory;
import com.thinh.project.Shoptech.payload.request.ProductPriceRequest;
import com.thinh.project.Shoptech.payload.request.ProductRequest;
import com.thinh.project.Shoptech.payload.request.SingleProductRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.CategoryRepository;
import com.thinh.project.Shoptech.repository.FeedBackRepository;
import com.thinh.project.Shoptech.repository.ImageRepository;
import com.thinh.project.Shoptech.repository.ProductRepository;
import com.thinh.project.Shoptech.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ImageServiceImpl imageServiceImpl;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	FeedBackRepository feedBackRepository;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public ApiResponse deleteProductById(DeleteRequest req) {
		System.out.println("result");
		ApiResponse apiResponse = new ApiResponse();
		Product product = productRepository.findById(req.getId()).orElse(null);

		if(!product.getOrderDetails().isEmpty()) {
			apiResponse.setMessage("Can not delete product because product have been ordered by customer");
			apiResponse.setSuccess(false);
			return apiResponse;
		
		}
		if (!product.getFeedback().isEmpty()) {
			feedBackRepository.deleteAll(product.getFeedback());
			imageRepository.deleteAll(product.getImages());
			productRepository.delete(product);
			apiResponse.setMessage("Product deleted successfully");
			return apiResponse;
		}
		imageRepository.deleteAll(product.getImages());
		productRepository.delete(product);
		apiResponse.setMessage("Product deleted successfully");
		apiResponse.setSuccess(true);
		return apiResponse;
	}

	@Override
	public ApiResponse addProduct(ProductRequest productRequest) {
		ApiResponse apiResponse = new ApiResponse();

		Category category = new Category();
		category.setId(productRequest.getCategoryId());
		Product product = new Product();

		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setOffer(productRequest.getOffer());
		product.setStatus(productRequest.getStatus());
		product.setCategories(category);
		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());

		product = productRepository.save(product);

		List<Image> images = new ArrayList<Image>();
		for (String image : productRequest.getImage()) {
			Image image2 = new Image();
			image2.setProduct(product);
			image2.setUrl(image);
			images.add(image2);
		}
		images = imageRepository.saveAll(images);

		product.setImages(images);
		apiResponse.setData(product);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Add product seccess!!!");
		return apiResponse;
	}

	@Override
	public ApiResponse updateProduct(ProductRequest productRequest) {
		ApiResponse apiResponse = new ApiResponse();
		Product product = productRepository.findById(productRequest.getId()).orElse(null);

		Category category = new Category();
		category.setId(productRequest.getCategoryId());
		product.setCategories(category);
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setOffer(productRequest.getOffer());
		product.setStatus(productRequest.getStatus());
		product.setUpdatedAt(new Date());
		product = productRepository.save(product);

		// imageRepository.deleteImage(product.getId());

		// imageRepository.deleteById(product.getId());

		imageRepository.deleteAll(product.getImages());

		List<Image> images = new ArrayList<Image>();
		for (String image : productRequest.getImage()) {

			Image image2 = new Image();
			image2.setProduct(product);
			image2.setUrl(image);
			images.add(image2);
		}
		images = imageRepository.saveAll(images);
		product.setImages(images);
		apiResponse.setData(product);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Update product seccess!!!");

		return apiResponse;
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public SingleProductRequest getProductByProductId(OrderIdRequest req) {
		Product product = productRepository.findById(req.getUserIdReq()).orElse(null);
		SingleProductRequest productPriceRequest = new SingleProductRequest();
		productPriceRequest.setPCategory(product.getCategories().getName());

		productPriceRequest.set_id(product.getId());
		productPriceRequest.setPName(product.getName());
		productPriceRequest.setPDescription(product.getDescription());
		productPriceRequest.setPPrice(product.getPrice());
		productPriceRequest.setPOffer(product.getOffer());
		productPriceRequest.setPSold(product.getSold());
		productPriceRequest.setPQuantity(product.getQuantity());
		productPriceRequest.setCalculation_unit("Cái");
		productPriceRequest.setPStatus(product.getStatus());

		List<Image> img = product.getImages();
		List<String> imgages = new ArrayList<String>();
		for (Image item : img) {
			String url = item.getUrl();
			imgages.add(url);
		}
		productPriceRequest.setPImages(imgages);

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
		productPriceRequest.setPRatingsReviews(result);

		return productPriceRequest;

	}

	@Override
	public Product UpdateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			Category category = new Category();
			category.setId(productRequest.getCategoryId());
			Product existingProduct = optionalProduct.get();
			existingProduct.setName(productRequest.getName());
			existingProduct.setDescription(productRequest.getDescription());
			existingProduct.setPrice(productRequest.getPrice());
			existingProduct.setQuantity(productRequest.getQuantity());
			existingProduct.setOffer(productRequest.getOffer());
			existingProduct.setStatus(productRequest.getStatus());
			existingProduct.setCategories(category);

			Product Product = productRepository.save(existingProduct);
			return Product;
		} else {
			throw new NoSuchElementException("Product not found with id: " + id);
		}
	}

	public ProductRequest getJson(String product, List<MultipartFile> file) {
		ProductRequest productJson = new ProductRequest();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			productJson = objectMapper.readValue(product, ProductRequest.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		return productJson;
	}

	public List<FilterPriceProductRequest1> FilterProductByPrice(FilterPriceProductRequest filterPrice) {

		Double productPrice = filterPrice.getProductPrice();

		if (productPrice == null) {
			return new ArrayList<FilterPriceProductRequest1>();
		}

		List<FilterPriceProductRequest1> productLists = new ArrayList<FilterPriceProductRequest1>();

		List<Product> FiltedProduct = productRepository.findProductByPrice(productPrice);

		if (!FiltedProduct.isEmpty()) {
			for (Product priceProduct : FiltedProduct) {

				FilterPriceProductRequest1 priceRequest = new FilterPriceProductRequest1();

				priceRequest.setCategories(priceProduct.getCategories().getName());

				priceRequest.setId(priceProduct.getId());
				priceRequest.setName(priceProduct.getName());
				priceRequest.setDescription(priceProduct.getDescription());
				priceRequest.setPrice(priceProduct.getPrice());
				priceRequest.setOffer(priceProduct.getOffer());
				priceRequest.setSold(priceProduct.getSold());
				priceRequest.setQuantity(priceProduct.getQuantity());
				priceRequest.setCalculation_unit("Cái");
				priceRequest.setStatus(priceProduct.getStatus());
				;

				List<Image> img = priceProduct.getImages();

				List<String> imgages = new ArrayList<String>();
				for (Image item : img) {
					String url = item.getUrl();
					imgages.add(url);
				}
				priceRequest.setPimage(imgages);

				List<Feedback> feedbacks = priceProduct.getFeedback();
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
				priceRequest.setFeedbackDtos(result);
				productLists.add(priceRequest);
			}

		}
		return productLists;

	}

	@Override
	public List<ProductByCategory> getProductByCategory(OrderIdRequest req) {

		Category cate = categoryRepository.findById(req.getUserIdReq()).orElse(null);

		List<ProductByCategory> productList = new ArrayList<ProductByCategory>();
		List<Product> products = cate.getProducts();
		ProductByCategory dto = null;
		for (Product product : products) {
			dto = new ProductByCategory();

			dto.setPCategory(product.getCategories().getName());
			dto.set_id(product.getId());
			dto.setPName(product.getName());
			dto.setPDescription(product.getDescription());
			dto.setPPrice(product.getPrice());
			dto.setPOffer(product.getOffer());
			dto.setPSold(product.getSold());
			dto.setPQuantity(0);
			dto.setCalculation_unit("Cái");
			dto.setPStatus(product.getStatus());

			List<Image> img = product.getImages();
			List<String> imgages = new ArrayList<String>();
			for (Image item : img) {
				String url = item.getUrl();
				imgages.add(url);
			}
			dto.setPImages(imgages);

			List<Feedback> feedbacks = product.getFeedback();
			List<FeedbackDto> result = new ArrayList<>();
			FeedbackDto fb = null;
			for (Feedback item : feedbacks) {
				fb = new FeedbackDto();
				fb.setUser(item.getId());
				fb.setCreatedAt(new Date());
				fb.setRating(item.getVote());
				fb.setReview(item.getComment());
				fb.setUser(item.getUser().getId());
				result.add(fb);
			}
			dto.setPRatingsReviews(result);
			productList.add(dto);
		}

		return productList;
	}

}
