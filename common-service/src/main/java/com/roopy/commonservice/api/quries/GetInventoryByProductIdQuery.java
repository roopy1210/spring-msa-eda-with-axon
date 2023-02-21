package com.roopy.commonservice.api.quries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInventoryByProductIdQuery {
    private String productId;
}
