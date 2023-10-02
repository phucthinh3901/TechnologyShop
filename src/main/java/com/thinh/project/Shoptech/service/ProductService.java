package com.thinh.project.Shoptech.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.dto.ProductDto;
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

public interface ProductService {

	// Product addProduct(ProductRequest productRequest);

	ApiResponse addProduct(ProductRequest productRequest);

	SingleProductRequest getProductByProductId(OrderIdRequest req);

	Product UpdateProduct(Integer id, ProductRequest productRequest);

	Product getProductById(Integer id);

	ApiResponse deleteProductById(DeleteRequest id);

	List<Product> getAllProduct();

	ProductRequest getJson(String user, List<MultipartFile> file);

	List<FilterPriceProductRequest1> FilterProductByPrice(FilterPriceProductRequest filer);

	List<ProductByCategory> getProductByCategory(OrderIdRequest req);

	ApiResponse updateProduct(ProductRequest productRequest);
}
