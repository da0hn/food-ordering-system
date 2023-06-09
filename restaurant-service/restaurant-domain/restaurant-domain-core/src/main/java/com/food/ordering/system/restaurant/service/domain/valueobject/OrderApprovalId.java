package com.food.ordering.system.restaurant.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
  protected OrderApprovalId(final UUID value) {
    super(value);
  }

  public static OrderApprovalId of(final UUID value) {
    return new OrderApprovalId(value);
  }

  public static OrderApprovalId newInstance() {
    return new OrderApprovalId(UUID.randomUUID());
  }
}
