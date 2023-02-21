package com.roopy.commonservice.api.events;

import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent {

    private String paymentId;
    private String orderId;
    private int totalPaymentAmt;
    private List<PaymentDetailDTO> paymentDetails;

}
