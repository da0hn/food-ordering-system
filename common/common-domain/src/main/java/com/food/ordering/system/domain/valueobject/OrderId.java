package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {

  protected OrderId(final UUID value) {
    super(value);
  }

  public static OrderId newInstance() {
    return new OrderId(UUID.randomUUID());
  }

  public static OrderId of(final UUID value) {
    return new OrderId(value);
  }

}
