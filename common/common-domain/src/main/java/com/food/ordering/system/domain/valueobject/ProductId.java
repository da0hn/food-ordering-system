package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> {

  protected ProductId(final UUID value) {
    super(value);
  }

  public static ProductId of(final UUID value) {
    return new ProductId(value);
  }

}
