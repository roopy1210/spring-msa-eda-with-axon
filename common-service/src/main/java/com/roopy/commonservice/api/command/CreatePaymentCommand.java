package com.roopy.commonservice.api.command;

import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
public class CreatePaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private int totalPaymentAmt;
    private List<PaymentDetailDTO> paymentDetails;

}
