package com.roopy.commonservice.api.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDeliveriedEvent {

    private String orderId;
    private String deliveryId;
    private String deliveryStatus;

}
