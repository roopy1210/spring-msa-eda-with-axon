package com.roopy.paymentservice.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 8968020395135360783L;

    @Id
    @Column(name = "payment_id", nullable = false, length = 15)
    private String paymentId;

    @Column(name = "order_id", nullable = false, length = 15)
    private String orderId;

    @Column(name = "total_payment_amt", nullable = false)
    private int totalPaymentAmt;

    @Column(name = "payment_status", nullable = false, length = 2)
    private String paymentStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", updatable = false)
    private List<PaymentDetail> paymentDetails;

}
