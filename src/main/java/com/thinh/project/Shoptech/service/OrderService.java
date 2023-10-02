package com.thinh.project.Shoptech.service;

import java.util.List;

import com.thinh.project.Shoptech.dto.OrdersDto;
import com.thinh.project.Shoptech.entity.Order;
import com.thinh.project.Shoptech.payload.request.DeleteRequest;
import com.thinh.project.Shoptech.payload.request.OrderIdRequest;
import com.thinh.project.Shoptech.payload.request.OrderRequest;
import com.thinh.project.Shoptech.payload.request.UpdateStatusOrderRequest;
import com.thinh.project.Shoptech.payload.response.ApiResponse;

public interface OrderService {

	Order createOrder(OrderRequest orderRequest);
	
	ApiResponse updateOrder(UpdateStatusOrderRequest orderRequest);
	
	ApiResponse deleteOrder(DeleteRequest req);
	
	List<OrdersDto> getAllOrders();

	List<OrdersDto> getOrdersById(OrderIdRequest idRequest);
}
