package com.roopy.deliveryservice.api.repository;

import com.roopy.deliveryservice.api.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
}
