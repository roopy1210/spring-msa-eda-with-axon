package com.roopy.orderservice.api.events;

import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import com.roopy.orderservice.api.dto.OrderDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private String orderStatus;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private String paymentId;
    private List<PaymentDetailDTO> paymentDetails;
    private int totalPaymentAmt;
}
