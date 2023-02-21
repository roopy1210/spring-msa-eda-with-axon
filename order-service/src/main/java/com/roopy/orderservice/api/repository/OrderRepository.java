package com.roopy.orderservice.api.repository;

import com.roopy.orderservice.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
