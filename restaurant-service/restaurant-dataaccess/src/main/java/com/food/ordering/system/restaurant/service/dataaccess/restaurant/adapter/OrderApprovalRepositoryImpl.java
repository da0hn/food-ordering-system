package com.food.ordering.system.restaurant.service.dataaccess.restaurant.adapter;

import com.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.repository.OrderApprovalJpaRepository;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.repository.OrderApprovalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {

  private final RestaurantDataAccessMapper restaurantDataAccessMapper;
  private final OrderApprovalJpaRepository orderApprovalJpaRepository;

  @Override
  public OrderApproval save(final OrderApproval orderApproval) {
    final var orderApprovalEntity = this.restaurantDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval);
    final var savedOrderApprovalEntity = this.orderApprovalJpaRepository.save(orderApprovalEntity);
    return this.restaurantDataAccessMapper.orderApprovalEntityToOrderApproval(savedOrderApprovalEntity);
  }
}
