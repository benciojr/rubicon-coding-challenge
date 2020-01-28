package com.rubicon.app.api.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rubicon.app.model.OrderDetail;
import com.rubicon.app.model.OrderStatus;

public class DummyOrders {

    private List<OrderDetail> ordersList;
    private static DummyOrders instance;

    public static DummyOrders getInstance() {
        if (instance == null) {
            synchronized (DummyOrders.class) {
                instance = new DummyOrders();
            }
        }
        return instance;
    }

    private DummyOrders() {
        ordersList = new ArrayList<OrderDetail>();
	LocalDateTime currentTime = LocalDateTime.now();
        ordersList.add(new OrderDetail(1, 20, currentTime, 60000, OrderStatus.REQUESTED));
        ordersList.add(new OrderDetail(2, 21, currentTime, 60000, OrderStatus.IN_PROGRESS));
        ordersList.add(new OrderDetail(3, 22, currentTime, 15000, OrderStatus.DELIVERED));
        ordersList.add(new OrderDetail(4, 23, currentTime, 20000, OrderStatus.CANCELLED));
        ordersList.add(new OrderDetail(5, 24, currentTime, 120000, OrderStatus.REQUESTED));
        ordersList.add(new OrderDetail(6, 25, currentTime, 120000, OrderStatus.IN_PROGRESS));
        ordersList.add(new OrderDetail(7, 26, currentTime, 15000, OrderStatus.DELIVERED));
        ordersList.add(new OrderDetail(8, 27, currentTime, 20000, OrderStatus.CANCELLED));
        ordersList.add(new OrderDetail(9, 28, currentTime, 180000, OrderStatus.REQUESTED));
        ordersList.add(new OrderDetail(10, 29, currentTime, 180000, OrderStatus.IN_PROGRESS));
        ordersList.add(new OrderDetail(11, 30, currentTime, 15000, OrderStatus.DELIVERED));
        ordersList.add(new OrderDetail(12, 31, currentTime, 20000, OrderStatus.CANCELLED));
    }

    public List<OrderDetail> getOrdersList() {
        return ordersList;
    }
}