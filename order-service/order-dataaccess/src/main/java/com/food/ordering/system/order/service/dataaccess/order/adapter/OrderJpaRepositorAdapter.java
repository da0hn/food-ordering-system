package com.food.ordering.system.order.service.dataaccess.order.adapter;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.valueobject.TrackingId;
import com.food.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.order.repository.OrderJpaRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderJpaRepositorAdapter implements OrderRepository {

  private final OrderJpaRepository repository;
  private final OrderDataAccessMapper mapper;

  @Override
  public Optional<Order> save(final Order order) {
    if (Objects.isNull(order)) return Optional.empty();
    final var createdOrderEntity = this.repository.save(this.mapper.orderToOrderEntity(order));
    return Optional.ofNullable(this.mapper.orderEntityToOrder(createdOrderEntity));
  }

  @Override
  public Optional<Order> findByTrackingId(final TrackingId trackingId) {
    return this.repository.findByTrackingId(trackingId.getValue())
      .map(this.mapper::orderEntityToOrder);
  }

}
