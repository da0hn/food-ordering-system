package com.food.ordering.system.restaurant.service.domain.ports.spi.message.repository;

import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {

  OrderApproval save(OrderApproval orderApproval);

}
