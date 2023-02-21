package com.roopy.commonservice.api.command;

import com.roopy.commonservice.api.dto.OrderStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus = OrderStatus.CANCELD.value();

}
