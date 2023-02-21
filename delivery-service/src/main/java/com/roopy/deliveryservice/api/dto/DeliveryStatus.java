package com.roopy.deliveryservice.api.dto;

public enum DeliveryStatus {
    REQUESTED("10"),
    CANCELD("20"),
    COMPLETED("30");

    private String value;

    DeliveryStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
