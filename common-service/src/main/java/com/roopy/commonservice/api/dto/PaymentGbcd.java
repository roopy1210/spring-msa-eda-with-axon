package com.roopy.commonservice.api.dto;

public enum PaymentGbcd {
    CARD("10"),
    POINT("20");

    private String value;

    PaymentGbcd(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
