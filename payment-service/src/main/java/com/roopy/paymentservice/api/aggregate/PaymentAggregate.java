package com.roopy.paymentservice.api.aggregate;

import com.roopy.commonservice.api.command.CancelPaymentCommand;
import com.roopy.commonservice.api.command.CreatePaymentCommand;
import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import com.roopy.commonservice.api.events.PaymentCancelledEvent;
import com.roopy.commonservice.api.events.PaymentProcessedEvent;
import com.roopy.paymentservice.api.entity.Payment;
import com.roopy.paymentservice.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private int totalPaymentAmt;
    private String paymentStatus;
    private List<PaymentDetailDTO> paymentDetails;

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand createPaymentCommand) {
        log.info("[@CommandHandler] Executing PaymentAggregate..");
        log.info(createPaymentCommand.toString());

        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(
                createPaymentCommand.getPaymentId(),
                createPaymentCommand.getOrderId(),
                createPaymentCommand.getTotalPaymentAmt(),
                createPaymentCommand.getPaymentDetails()
        );

        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        log.info("[@EventSourcingHandler] Executing on..");
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
        this.totalPaymentAmt = event.getTotalPaymentAmt();
        this.paymentDetails = event.getPaymentDetails();
    }

    @CommandHandler
    public void handle(CancelPaymentCommand cancelPaymentCommand) {
        log.info("Executing CancelPaymentCommand for Order Id : {} and Payment Id: {}",
                cancelPaymentCommand.getOrderId(), cancelPaymentCommand.getPaymentId());

        PaymentCancelledEvent paymentCancelledEvent = new PaymentCancelledEvent();
        BeanUtils.copyProperties(cancelPaymentCommand, paymentCancelledEvent);

        AggregateLifecycle.apply(paymentCancelledEvent);
    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent event) {
        log.info("Executing PaymentCancelledEvent for Order Id : {} and Payment Id: {}",
                event.getOrderId(), event.getPaymentId());

        this.paymentStatus = event.getPaymentStatus();
    }
}
