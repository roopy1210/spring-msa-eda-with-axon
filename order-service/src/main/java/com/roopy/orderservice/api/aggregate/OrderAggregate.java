package com.roopy.orderservice.api.aggregate;

import com.roopy.commonservice.api.command.CancelOrderCommand;
import com.roopy.commonservice.api.command.CompleteOrderCommand;
import com.roopy.commonservice.api.dto.PaymentDetailDTO;
import com.roopy.commonservice.api.events.OrderCancelledEvent;
import com.roopy.commonservice.api.events.OrderCompletedEvent;
import com.roopy.orderservice.api.command.CreateOrderCommand;
import com.roopy.orderservice.api.dto.OrderDetailDTO;
import com.roopy.orderservice.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Slf4j
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String userId;
    private String orderStatus;
    private int totalOrderAmt;
    private List<OrderDetailDTO> orderDetails;
    private String paymentId;
    private List<PaymentDetailDTO> paymentDetails;
    private int totalPaymentAmt;

    public OrderAggregate() {

    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("[@CommandHandler] Executing OrderAggregate");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(createOrderCommand.getOrderId());
        orderCreatedEvent.setUserId(createOrderCommand.getUserId());
        orderCreatedEvent.setOrderStatus(createOrderCommand.getOrderStatus());
        orderCreatedEvent.setTotalOrderAmt(createOrderCommand.getTotalOrderAmt());
        orderCreatedEvent.setOrderDetails(createOrderCommand.getOrderDetails());
        orderCreatedEvent.setPaymentId(createOrderCommand.getPaymentId());
        orderCreatedEvent.setPaymentDetails(createOrderCommand.getPaymentDetails());
        orderCreatedEvent.setTotalPaymentAmt(createOrderCommand.getTotalPaymentAmt());
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("[@EventSourcingHandler] Executing OrderAggregate");
        this.orderId = orderCreatedEvent.getOrderId();
        this.userId = orderCreatedEvent.getUserId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        this.totalOrderAmt = orderCreatedEvent.getTotalOrderAmt();
        this.orderDetails = orderCreatedEvent.getOrderDetails();
        this.paymentId = orderCreatedEvent.getPaymentId();
        this.paymentDetails = orderCreatedEvent.getPaymentDetails();
        this.totalPaymentAmt = orderCreatedEvent.getTotalPaymentAmt();
    }

    @CommandHandler
    public void on(CompleteOrderCommand completeOrderCommand) {
        log.info("[@CommandHandler] Executing CompleteOrderCommand");

        OrderCompletedEvent orderCompletedEvent =OrderCompletedEvent.builder()
                .orderId(completeOrderCommand.getOrderId())
                .build();

        AggregateLifecycle.apply(orderCompletedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        log.info("[@EventSourcingHandler] Executing OrderCompletedEvent");
    }

    @CommandHandler
    public void handle(CancelOrderCommand cancelOrderCommand) {
        log.info("[@CommandHandler] Executing CancelOrderCommand in OrderAggregate");

        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent();
        BeanUtils.copyProperties(cancelOrderCommand,orderCancelledEvent);

        AggregateLifecycle.apply(orderCancelledEvent);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        log.info("[@EventSourcingHandler] Executing OrderCancelledEvent in OrderAggregate");

        this.orderStatus = event.getOrderStatus();
    }
}
