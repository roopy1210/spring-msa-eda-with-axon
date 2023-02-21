package com.roopy.commonservice.api.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCompletedEvent {
    private String orderId;
    private String orderSatus;
}
