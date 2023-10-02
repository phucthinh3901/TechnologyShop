package com.thinh.project.Shoptech.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinh.project.Shoptech.dto.OrdersDto;
import com.thinh.project.Shoptech.dto.UserDto;
import com.thinh.project.Shoptech.entity.EstatusOrder;
import com.thinh.project.Shoptech.entity.Image;
import com.thinh.project.Shoptech.entity.Order;
import com.thinh.project.Shoptech.entity.OrderDetail;
import com.thinh.project.Shoptech.entity.OrderDetailId;
import com.thinh.project.Shoptech.entity.OrderStatus;
import com.thinh.project.Shoptech.entity.Product;
import com.thinh.project.Shoptech.entity.User;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.request.OrderRequest;
import com.thinh.project.Shoptech.payload.request.UpdateStatusOrderRequest;
import com.thinh.project.Shoptech.payload.request.allProduct;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.repository.OrderDetailsRepository;
import com.thinh.project.Shoptech.repository.OrderRepository;
import com.thinh.project.Shoptech.repository.OrderStatusRepository;
import com.thinh.project.Shoptech.repository.ProductRepository;
import com.thinh.project.Shoptech.repository.UserRepository;
import com.thinh.project.Shoptech.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Order createOrder(OrderRequest orderRequest) {

		Double totalPrice = 0D;
		Order order = new Order();
		User user = userRepository.findById(orderRequest.getUser()).orElse(null);
		order.setUser(user);
		order.setTransactionId(orderRequest.getTransactionId());
		order.setAddress(orderRequest.getAddress());
		order.setPhone(orderRequest.getPhone());
		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());
		order.setAmount(totalPrice);
		OrderStatus status = new OrderStatus();
		status.setType(EstatusOrder.Not_processed);
		status = orderStatusRepository.save(status);
		order.setStatus(status);
		order = orderRepository.save(order);

		OrderDetail detail = new OrderDetail();
		OrderDetailId orderDetailId = new OrderDetailId();
		Product products = new Product();

		for (allProduct product : orderRequest.getAllProduct()) {

			products = productRepository.findById(product.getId()).orElse(null);
			totalPrice += products.getPrice() * product.getQuantity();

			detail = new OrderDetail();
			orderDetailId = new OrderDetailId();
			orderDetailId.setOrders(order);
			orderDetailId.setProducts(products);

			detail.setId(orderDetailId);
			detail.setQuantity(product.getQuantity());

			int quantity = products.getQuantity() - detail.getQuantity();
			products.setQuantity(quantity);
			productRepository.save(products);

			orderDetailsRepository.save(detail);
		}
		order.setAmount(totalPrice);
		return orderRepository.save(order);
	}

	@Override
	public ApiResponse updateOrder(UpdateStatusOrderRequest orderRequest) {
		ApiResponse response = new ApiResponse();
		Order order = orderRepository.findById(orderRequest.getOrderIdRequest()).orElse(null);
		if (order != null) {
			order.setStatus(order.getStatus());
			order.setUpdatedAt(new Date());
			OrderStatus status = order.getStatus();

			if (orderRequest.getOrderStatus() != order.getStatus().getType().toString()) {
				if (orderRequest.getOrderStatus().equals("Processing")) {
					status.setType(status.getType().Processing);
				} else if (orderRequest.getOrderStatus().equals("Not_processed")) {
					status.setType(status.getType().Not_processed);
				} else if (orderRequest.getOrderStatus().equals("Shipped")) {
					status.setType(status.getType().Shipped);
				} else if (orderRequest.getOrderStatus().equals("Delivered")) {
					status.setType(status.getType().Delivered);
				} else if (orderRequest.getOrderStatus().equals("Cancelled")) {
					status.setType(status.getType().Cancelled);
				} else if (orderRequest.getOrderStatus().equals("chonhaphang")) {
					status.setType(status.getType().chonhaphang);
				}
			} else {
				response.setMessage("Status is existing!!!");
				response.setSuccess(false);

				return response;

			}

			order.setStatus(status);
			orderRepository.save(order);
			response.setMessage("Order updated successfully");
			response.setData(status);
			response.setSuccess(true);
		}
		return response;

	}

	public ApiResponse deleteOrder(DeleteRequest req) {
		ApiResponse apiResponse = new ApiResponse();
		Order order = orderRepository.findById(req.getId()).orElse(null);
		Integer os = order.getStatus().getId();

		List<OrderDetail> details = orderDetailsRepository.findAll();
		for (OrderDetail orderDetail : details) {
			if (orderDetail.getId().getOrders().getId() == order.getId()) {
				orderDetailsRepository.delete(orderDetail);
			}

		}
		orderRepository.deleteById(order.getId());
		orderStatusRepository.deleteById(os);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Order deleted successfully");
		apiResponse.setData(order);

		return apiResponse;
	}

	@Override
	public List<OrdersDto> getAllOrders() {

		List<Order> orders = orderRepository.findAll();
		List<OrdersDto> orderDto = new ArrayList<OrdersDto>();
		OrdersDto ordersDto = null;
		List<allProduct> allProducts = null;
		allProduct p = null;
		Product products = null;
		UserDto dto = null;
		for (Order order : orders) {
			ordersDto = new OrdersDto();
			ordersDto.set_idOrder(order.getId());
			ordersDto.setAddress(order.getAddress());
			ordersDto.setAmount(order.getAmount());
			ordersDto.setCreatedAt(order.getCreatedAt());
			ordersDto.setPhone(order.getPhone());
			ordersDto.setStatus(order.getStatus().getType().toString());
			ordersDto.setTransactionId(order.getTransactionId());
			ordersDto.setUpdatedAt(order.getUpdatedAt());
			User user = userRepository.findById(order.getUser().getId()).get();
			dto = new UserDto();
			dto.set_id(user.getId());
			dto.setName(user.getUsername());
			dto.setEmail(user.getEmail());
			ordersDto.setUser(dto);
			allProducts = new ArrayList<allProduct>();
			for (OrderDetail detail : order.getOrderDetails()) {
				products = productRepository.findById(detail.getId().getProducts().getId()).orElse(null);
				p = new allProduct();
				p.setId(products.getId());
				p.setPname(products.getName());
				p.setQuantity(products.getQuantity());
				List<Image> images = products.getImages();
				List<String> img = new ArrayList<String>();
				for (Image image : images) {
					String url = image.getUrl();
					img.add(url);
				}
				p.setImageProduct(img);
				allProducts.add(p);
			}
			ordersDto.setAllProduct(allProducts);
			orderDto.add(ordersDto);
		}

		return orderDto;
	}

	@Override
	public List<OrdersDto> getOrdersById(OrderIdRequest OrderId) {

		User user = userRepository.findById(OrderId.getUserIdReq()).orElse(null);

		List<OrdersDto> orderDto = new ArrayList<OrdersDto>();

		OrdersDto ordersDto = null;

		UserDto dto = null;

		List<allProduct> allProducts = null;
		allProduct p = null;
		Product products = null;

		for (Order order : user.getOrders()) {
			ordersDto = new OrdersDto();
			ordersDto = new OrdersDto();
			ordersDto.set_idOrder(order.getId());
			ordersDto.setAddress(order.getAddress());
			ordersDto.setAmount(order.getAmount());
			ordersDto.setCreatedAt(order.getCreatedAt());
			ordersDto.setPhone(order.getPhone());
			ordersDto.setStatus(order.getStatus().getType().toString());
			ordersDto.setTransactionId(order.getTransactionId());
			ordersDto.setUpdatedAt(order.getUpdatedAt());

			// user = userRepository.findById(order.getUser().getId()).get();

			dto = new UserDto();
			dto.set_id(user.getId());
			dto.setName(user.getUsername());
			dto.setEmail(user.getEmail());
			ordersDto.setUser(dto);
			allProducts = new ArrayList<allProduct>();
			for (OrderDetail detail : order.getOrderDetails()) {
				products = productRepository.findById(detail.getId().getProducts().getId()).orElse(null);
				p = new allProduct();
				p.setId(products.getId());
				p.setPname(products.getName());
				p.setQuantity(products.getQuantity());
				List<Image> images = products.getImages();
				List<String> img = new ArrayList<String>();
				for (Image image : images) {
					String url = image.getUrl();
					img.add(url);
				}
				p.setImageProduct(img);
				allProducts.add(p);
			}
			ordersDto.setAllProduct(allProducts);
			orderDto.add(ordersDto);
		}

		return orderDto;
	}
}
