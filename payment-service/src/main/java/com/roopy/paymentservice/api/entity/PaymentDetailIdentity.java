package com.roopy.paymentservice.api.entity;

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
public class PaymentDetailIdentity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2220920152303880603L;

    @Column(name = "payment_id", nullable = false, length = 15)
    private String paymentId;

    @Column(name = "payment_gbcd", nullable = false, length = 2)
    private String paymentGbcd;
}
