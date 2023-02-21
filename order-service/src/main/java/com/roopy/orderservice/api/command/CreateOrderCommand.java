package com.roopy.orderservice.api.command;

import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import com.roopy.orderservice.api.dto.OrderDetailDTO;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String userId;
    private String orderStatus;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private String paymentId;
    private List<PaymentDetailDTO> paymentDetails;
    private int totalPaymentAmt;

}
