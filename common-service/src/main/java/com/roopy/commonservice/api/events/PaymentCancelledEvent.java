package com.roopy.commonservice.api.events;

import lombok.Data;

@Data
public class PaymentCancelledEvent {
    private String paymentId;
    private String orderId;
    private String paymentStatus;
}