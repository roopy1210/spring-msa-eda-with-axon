package com.roopy.commonservice.api.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class DeliveryOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String deliveryId;
}
