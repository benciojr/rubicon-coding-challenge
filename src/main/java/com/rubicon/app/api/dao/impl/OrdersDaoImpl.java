package com.rubicon.app.api.dao.impl;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.UnsupportedOperationException;

import com.rubicon.app.api.dao.OrdersDao;
import com.rubicon.app.api.data.DummyOrders;
import com.rubicon.app.api.service.OrdersService;
import com.rubicon.app.model.OrderDetail;
import com.rubicon.app.model.OrderStatus;
import com.rubicon.app.model.ResponseObject;

import org.springframework.context.annotation.Bean;

public class OrdersDaoImpl implements OrdersDao {

    private List<OrderDetail> getDummyData() {
        return DummyOrders.getInstance().getOrdersList();
    }

    @Override
    public List<OrderDetail> findAll() {
        return getDummyData();
    }

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return getDummyData().stream()
                             .filter(order -> order.getOrderId() == Long.valueOf(orderId))
                             .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetail> findByFarmId(String farmId) {
        return getDummyData().stream()
                             .filter(order -> order.getFarmId() == Long.valueOf(farmId))
                             .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetail> findByStatus(String status) {
        return getDummyData().stream()
                             .filter(order -> status.equalsIgnoreCase(order.getOrderStatus().toString()))
                             .collect(Collectors.toList());
    }

    @Override
    public OrderStatus getStatusCodeByOrderId(Long orderId) {
        OrderStatus status = null;
        if (findByOrderId(orderId.toString()).get(0) != null) {
            status = findByOrderId(orderId.toString()).get(0).getOrderStatus();
        }
        return status;
    }

    @Override
    public void cancelOrder(Long orderId) {
        if (findByOrderId(orderId.toString()).get(0) != null) {
            findByOrderId(orderId.toString()).get(0).setOrderStatus(OrderStatus.CANCELLED);
        }
    }

    @Override
    public OrderDetail placeOrder(OrderDetail orderDetail) {
        if (orderDetail.getDateTime() == null) {
            orderDetail.setDateTime(LocalDateTime.now());
        }
        if (!isOverlappingExistingOrders(orderDetail)) {
            orderDetail.setOrderStatus(OrderStatus.REQUESTED);
            getDummyData().add(orderDetail);
        } else {
            throw new UnsupportedOperationException("Invalid Operation - water orders for a farm should not overlap.");
        }
        return orderDetail;
    }

    private boolean isOverlappingExistingOrders(OrderDetail orderDetail) {

        boolean isOverlapping = false;
        LocalDateTime startOfDate1 = orderDetail.getDateTime();
        LocalDateTime endOfDate1 = startOfDate1.plus(orderDetail.getDuration(), ChronoUnit.MILLIS);

        List<OrderDetail> ordersList = findByFarmId(String.valueOf(orderDetail.getFarmId()));
        if (ordersList.size() > 0) {
            for (OrderDetail order : ordersList) {
                LocalDateTime startOfDate2 = order.getDateTime();
                LocalDateTime endOfDate2 = startOfDate2.plus(order.getDuration(), ChronoUnit.MILLIS);

                isOverlapping = isOverlapping(startOfDate1, endOfDate1, startOfDate2, endOfDate2);
                if (isOverlapping) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlapping(LocalDateTime startOfDate1, LocalDateTime endOfDate1, LocalDateTime startOfDate2, LocalDateTime endOfDate2) {
        return startOfDate1.isBefore(endOfDate2) && startOfDate2.isBefore(endOfDate1);
    }
}
