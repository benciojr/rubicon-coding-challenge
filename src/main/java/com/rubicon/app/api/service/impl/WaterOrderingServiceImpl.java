package com.rubicon.app.api.service.impl;

import java.util.List;
import java.lang.UnsupportedOperationException;

import com.rubicon.app.api.dao.OrdersDao;
import com.rubicon.app.api.dao.impl.OrdersDaoImpl;
import com.rubicon.app.api.service.OrdersService;
import com.rubicon.app.model.OrderDetail;
import com.rubicon.app.model.OrderStatus;
import com.rubicon.app.model.ResponseObject;

public class WaterOrderingServiceImpl implements OrdersService {

    @Override
    public OrderDetail placeOrder(OrderDetail orderDetail) {
        OrdersDao ordersDao = new OrdersDaoImpl();
        return ordersDao.placeOrder(orderDetail);
    }

    @Override
    public ResponseObject cancelOrder(Long orderId) {
        ResponseObject response = new ResponseObject("INFO", "Order Id " + orderId + " was successfully cancelled.");

        OrdersDao ordersDao = new OrdersDaoImpl();
        OrderStatus orderStatus = ordersDao.getStatusCodeByOrderId(orderId);
        if (orderStatus == OrderStatus.REQUESTED) {
            ordersDao.cancelOrder(orderId);
        } else if (orderStatus == OrderStatus.CANCELLED) {
            throw new UnsupportedOperationException("Invalid Operation - order id " + orderId + " is already cancelled.");
        } else if (orderStatus == OrderStatus.IN_PROGRESS) {
            throw new UnsupportedOperationException("Invalid Operation - order id " + orderId + " is already being delivered.");
        } else if (orderStatus == OrderStatus.DELIVERED) {
            throw new UnsupportedOperationException("Invalid Operation - order id " + orderId + " is already delivered.");
        }
        return response;
    }

    @Override
    public List<OrderDetail> viewOrders(String searchBy, String searchByValue) throws UnsupportedOperationException {
        List ordersList = null;
        OrdersDao ordersDao = new OrdersDaoImpl();
        if (searchBy.equalsIgnoreCase("all")) {
            ordersList = ordersDao.findAll();
        } else if (searchBy.equalsIgnoreCase("orderId")) {
            ordersList = ordersDao.findByOrderId(searchByValue);
        } else if (searchBy.equalsIgnoreCase("farmId")) {
            ordersList = ordersDao.findByFarmId(searchByValue);
        } else if (searchBy.equalsIgnoreCase("orderStatus")) {
            ordersList = ordersDao.findByStatus(searchByValue);
        } else {
            throw new UnsupportedOperationException("Invalid searchBy operation - " + searchBy);
        }
        return ordersList;
    }

}
