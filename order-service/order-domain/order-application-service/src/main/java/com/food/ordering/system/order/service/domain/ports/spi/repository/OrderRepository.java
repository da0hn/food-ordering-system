package com.food.ordering.system.order.service.domain.ports.spi.repository;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

  Optional<Order> save(Order order);

  Optional<Order> findByTrackingId(TrackingId trackingId);

  Optional<Order> findById(OrderId of);
}
