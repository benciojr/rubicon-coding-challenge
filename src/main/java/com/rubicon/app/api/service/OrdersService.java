package com.rubicon.app.api.service;

import java.util.List;

import com.rubicon.app.model.ResponseObject;
import com.rubicon.app.model.OrderDetail;

public interface OrdersService {
    OrderDetail placeOrder(OrderDetail orderDetail);
    List<OrderDetail> viewOrders(String searchBy, String searchByValue);
    ResponseObject cancelOrder(Long orderId);
}