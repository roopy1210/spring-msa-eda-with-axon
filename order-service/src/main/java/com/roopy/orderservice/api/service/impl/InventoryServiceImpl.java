package com.roopy.orderservice.api.service.impl;

import com.roopy.commonservice.api.quries.GetInventoryByProductIdQuery;
import com.roopy.commonservice.api.vo.InventoryVO;
import com.roopy.orderservice.api.events.OrderCreatedEvent;
import com.roopy.orderservice.api.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private transient QueryGateway queryGateway;

    @Override
    public boolean isValidInventory(OrderCreatedEvent event) {
        int totalRequestQty = 0;
        boolean isValidInventory = false;

        GetInventoryByProductIdQuery getInventoryByProductIdQuery = new GetInventoryByProductIdQuery(event.getOrderId());

        InventoryVO inventoryVO = null;
        try {
            inventoryVO = queryGateway.query(getInventoryByProductIdQuery, ResponseTypes.instanceOf(InventoryVO.class)).join();
            if (totalRequestQty <= inventoryVO.getInventoryQty()) {
                isValidInventory = true;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return isValidInventory;
    }
}
