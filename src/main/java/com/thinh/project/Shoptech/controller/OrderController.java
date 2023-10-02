package com.thinh.project.Shoptech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinh.project.Shoptech.dto.OrdersDto;
import com.thinh.project.Shoptech.entity.Order;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.request.OrderRequest;
import com.thinh.project.Shoptech.payload.request.UpdateStatusOrderRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.service.OrderService;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/create-order")
	public Order createOrder(@RequestBody OrderRequest orderRequest) {	
		return orderService.createOrder(orderRequest);
	}
	
	@PostMapping("/update-order")
	public ApiResponse updateOrderStatus(@RequestBody UpdateStatusOrderRequest orderRequest) {
		return orderService.updateOrder(orderRequest);
	}
	
	@PostMapping("/delete-order")
	private ApiResponse deleteOrder(@RequestBody DeleteRequest id) {
		return orderService.deleteOrder(id);
	}
	
	@GetMapping("/get-all-orders")
	private List<OrdersDto> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping("/order-by-user")
	private List<OrdersDto> getOrderById(@RequestBody OrderIdRequest id){
		return orderService.getOrdersById(id);
	}
	
	
}
