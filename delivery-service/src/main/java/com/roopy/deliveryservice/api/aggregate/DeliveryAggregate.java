package com.roopy.deliveryservice.api.aggregate;

import com.roopy.commonservice.api.command.DeliveryOrderCommand;
import com.roopy.commonservice.api.events.OrderDeliveriedEvent;
import com.roopy.deliveryservice.api.dto.DeliveryStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class DeliveryAggregate {

    @AggregateIdentifier
    private String deliveryId;
    private String orderId;
    private String deliveryStatus;

    public DeliveryAggregate() {

    }

    @CommandHandler
    public DeliveryAggregate(DeliveryOrderCommand deliveryOrderCommand) {
        log.info("Executing DeliveryAggregate for Order Id : {} and Delivery Id: {}",
                deliveryOrderCommand.getOrderId(), deliveryOrderCommand.getDeliveryId());
        OrderDeliveriedEvent orderDeliveriedEvent = OrderDeliveriedEvent.builder()
                        .orderId(deliveryOrderCommand.getOrderId())
                        .deliveryId(deliveryOrderCommand.getDeliveryId())
                        .deliveryStatus(DeliveryStatus.REQUESTED.value())
                        .build();

        AggregateLifecycle.apply(orderDeliveriedEvent);
    }

    @EventSourcingHandler
    public void on(OrderDeliveriedEvent orderDeliveriedEvent) {
        this.orderId = orderDeliveriedEvent.getOrderId();
        this.deliveryId = orderDeliveriedEvent.getDeliveryId();
        this.deliveryStatus = orderDeliveriedEvent.getDeliveryStatus();
    }

}
