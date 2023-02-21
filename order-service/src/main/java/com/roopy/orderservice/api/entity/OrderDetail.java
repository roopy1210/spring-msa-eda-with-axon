package com.roopy.orderservice.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -5630393476640097453L;

    @EmbeddedId
    private OrderDetailIdentity orderDetailIdentity;

    @Column(name = "product_id", nullable = false, length = 15)
    private String productId;

    @Column(name = "qty", nullable = false)
    private int qty;

    @Column(name = "order_amt", nullable = false)
    private int orderAmt;

}
