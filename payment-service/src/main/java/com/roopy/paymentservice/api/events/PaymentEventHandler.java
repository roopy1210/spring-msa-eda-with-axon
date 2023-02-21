package com.roopy.paymentservice.api.events;

import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import com.roopy.commonservice.api.dto.PaymentStatus;
import com.roopy.commonservice.api.events.PaymentCancelledEvent;
import com.roopy.commonservice.api.events.PaymentProcessedEvent;
import com.roopy.paymentservice.api.entity.Payment;
import com.roopy.paymentservice.api.entity.PaymentDetail;
import com.roopy.paymentservice.api.entity.PaymentDetailIdentity;
import com.roopy.paymentservice.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PaymentEventHandler {

    @Autowired
    private PaymentRepository paymentRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        log.info("[@EventHandler] Executing on..");
        log.info(event.toString());
        List<PaymentDetail> newPaymentDetails = new ArrayList<>();

        Payment payment = new Payment();
        payment.setPaymentId(event.getPaymentId());
        payment.setOrderId(event.getOrderId());
        payment.setTotalPaymentAmt(event.getTotalPaymentAmt());
        payment.setPaymentStatus(PaymentStatus.COMPLETED.value());

        for (PaymentDetailDTO paymentDetail:event.getPaymentDetails()) {
            PaymentDetailIdentity paymentDetailIdentity = new PaymentDetailIdentity(paymentDetail.getPaymentId(), paymentDetail.getPaymentGbcd());
            PaymentDetail newPaymentDetail = new PaymentDetail();
            newPaymentDetail.setPaymentDetailIdentity(paymentDetailIdentity);

            newPaymentDetails.add(newPaymentDetail);
        }
        payment.setPaymentDetails(newPaymentDetails);

        paymentRepository.save(payment);

    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        Payment payment = paymentRepository.findById(event.getPaymentId()).get();
        payment.setPaymentStatus(PaymentStatus.CANCELD.value());

        paymentRepository.save(payment);
    }

}
