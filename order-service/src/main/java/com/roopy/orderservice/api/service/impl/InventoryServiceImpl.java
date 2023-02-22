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
        int totalRequestQty = event.getOrderDetails().stream().mapToInt(o -> o.getQty()).sum();
        boolean isValidInventory = false;

        GetInventoryByProductIdQuery getInventoryByProductIdQuery = new GetInventoryByProductIdQuery(event.getOrderId());

        InventoryVO inventoryVO;
        try {
            inventoryVO = queryGateway.query(getInventoryByProductIdQuery, ResponseTypes.instanceOf(InventoryVO.class)).join();
            log.info("totalRequestQty : {}, inventoryVO.getInventoryQty : {}", totalRequestQty, inventoryVO.getInventoryQty());

            if (totalRequestQty <= inventoryVO.getInventoryQty() || inventoryVO.getInventoryQty() != 0) {
                isValidInventory = true;
            }
            log.info("재고여부 : {}", isValidInventory);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        return isValidInventory;
    }
}
