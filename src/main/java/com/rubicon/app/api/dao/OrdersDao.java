package com.rubicon.app.api.dao;

import java.util.List;
import com.rubicon.app.model.OrderDetail;
import com.rubicon.app.model.OrderStatus;

public interface OrdersDao {
    List<OrderDetail> findAll();
    List<OrderDetail> findByOrderId(String orderId);
    List<OrderDetail> findByFarmId(String farmId);
    List<OrderDetail> findByStatus(String status);
    OrderStatus getStatusCodeByOrderId(Long orderId);
    void cancelOrder(Long orderId);
    OrderDetail placeOrder(OrderDetail orderDetail);
}
