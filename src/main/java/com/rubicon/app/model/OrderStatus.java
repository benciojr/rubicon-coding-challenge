package com.rubicon.app.model;

public enum OrderStatus {
    REQUESTED,
    IN_PROGRESS,
    DELIVERED,
    CANCELLED;

    private static OrderStatus[] values = values();

    public static OrderStatus[] getValues() {
        return values;
    }
}