package com.roopy.deliveryservice.api.entity;

import com.roopy.deliveryservice.api.dto.DeliveryStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class Delivery implements Serializable {

    @Serial
    private static final long serialVersionUID = -8218615069842102049L;

    @Id
    @Column(name = "delivery_id", nullable = false, length = 15)
    private String deliveryId;

    @Column(name = "order_id", nullable = false, length = 15)
    private String orderId;

    @Column(name = "delivery_status", nullable = false, length = 2)
    private String deliveryStatus;

}
