package com.roopy.orderservice.api.dto;

import com.roopy.commonservice.api.dto.OrderStatus;
import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderId;
    private String userId;
    private OrderStatus orderStatus;
    private List<OrderDetailDTO> orderDetails;
    private String paymentId;
    private List<PaymentDetailDTO> paymentDetails;
}
