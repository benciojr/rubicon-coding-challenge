package com.rubicon.app.model;

import java.time.LocalDateTime;

public class OrderDetail {

    private long orderId;
    private long farmId;
    private LocalDateTime dateTime;
    private long duration;
    private OrderStatus orderStatus;
    private String orderStatusDescription;

    public OrderDetail(long orderId, long farmId, LocalDateTime dateTime, long duration, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.farmId = farmId;
        this.dateTime = dateTime;
        this.duration = duration;
        this.orderStatus = orderStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }

    public String getOrderStatusDescription() {
        switch (orderStatus) {
            case REQUESTED: return "Order has been placed but not yet delivered.";
            case IN_PROGRESS: return "Order is being delivered right now.";
            case DELIVERED: return "Order has been delivered.";
            case CANCELLED: return "Order was cancelled before delivery.";
        }
        return "";
    }

}
