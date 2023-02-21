package com.roopy.orderservice.api.events;

import com.roopy.commonservice.api.events.OrderCancelledEvent;
import com.roopy.commonservice.api.events.OrderCompletedEvent;
import com.roopy.orderservice.api.dto.OrderDetailDTO;
import com.roopy.commonservice.api.dto.OrderStatus;
import com.roopy.orderservice.api.entity.Order;
import com.roopy.orderservice.api.entity.OrderDetail;
import com.roopy.orderservice.api.entity.OrderDetailIdentity;
import com.roopy.orderservice.api.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OrderEventsHandler {

    @Autowired
    private OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("[@EventHandler] Executing on OrderCreatedEvent");
        List<OrderDetail> newOrderDetails = new ArrayList<>();

        Order order = new Order();
        order.setOrderId(event.getOrderId());
        order.setUserId(event.getUserId());
        order.setOrderStatus(OrderStatus.CREATED.value());
        order.setTotalOrderAmt(event.getTotalOrderAmt());

        for (OrderDetailDTO orderDetail:event.getOrderDetails()) {
            OrderDetailIdentity newOrderDetailIdentity = new OrderDetailIdentity(orderDetail.getOrderId(), orderDetail.getOrderSeq());
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setOrderDetailIdentity(newOrderDetailIdentity);
            newOrderDetail.setProductId(orderDetail.getProductId());
            newOrderDetail.setQty(orderDetail.getQty());
            newOrderDetail.setOrderAmt(orderDetail.getOrderAmt());

            newOrderDetails.add(newOrderDetail);
        }
        order.setOrderDetails(newOrderDetails);

        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        log.info("[@EventHandler] Executing on OrderCompletedEvent");

        // 주문정보조회
        Order order = orderRepository.findById(event.getOrderId()).get();

        Order newOrder = new Order();
        newOrder.setOrderId(event.getOrderId());
        newOrder.setUserId(order.getUserId());
        newOrder.setTotalOrderAmt(order.getTotalOrderAmt());
        newOrder.setOrderStatus(OrderStatus.APPROVED.value());

        orderRepository.save(newOrder);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        log.info("[@EventHandler] Executing OrderCancelledEvent in OrderEventsHandler");
        Order order = orderRepository.findById(event.getOrderId()).get();
        order.setOrderStatus(event.getOrderStatus());

        orderRepository.save(order);
    }
}
