package com.roopy.orderservice.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public final class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 3301186718958342588L;

    @Id
    @Column(name = "order_id", nullable = false, length = 15)
    private String orderId;

    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(name = "order_status", nullable = false, length = 2)
    private String orderStatus;

    @Column(name = "total_oarder_amt", nullable = false)
    private int totalOrderAmt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", updatable = false)
    private List<OrderDetail> orderDetails;

}
