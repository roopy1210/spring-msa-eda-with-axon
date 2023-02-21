package com.roopy.paymentservice.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "payment_detail")
public class PaymentDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -6466267141760451114L;

    @EmbeddedId
    private PaymentDetailIdentity paymentDetailIdentity;

    @Column(name = "payment_amt", nullable = false)
    private int paymentAmt;

}
