package com.roopy.orderservice.api.service;

import com.roopy.orderservice.api.events.OrderCreatedEvent;

public interface InventoryService {
    boolean isValidInventory(OrderCreatedEvent event);
}
