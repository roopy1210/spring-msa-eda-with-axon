package com.roopy.orderservice.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailIdentity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5881839298603846080L;

    @Column(name = "order_id", nullable = false, length = 15)
    private String orderId;

    @Column(name = "order_seq", nullable = false)
    private int orderSeq;
}
