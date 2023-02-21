package com.roopy.inventoryservice.api.projection;

import com.roopy.commonservice.api.quries.GetInventoryByProductIdQuery;
import com.roopy.commonservice.api.vo.InventoryVO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryProjection {

    @QueryHandler
    public InventoryVO getInventoryByProductId(GetInventoryByProductIdQuery query) {
        log.info("[QueryHandler] getInventoryByProductId in InventoryProjection for Product Id : {}", query.getProductId());

        // TODO:상품별 재고수량의 합을 반환처리 한다.
        InventoryVO inventoryVO = new InventoryVO(query.getProductId(), 20);
        return inventoryVO;
    }

}
