package com.roopy.commonservice.api.command;

import com.roopy.commonservice.api.dto.PaymentStatus;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelPaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus = PaymentStatus.CANCELD.value();

}
