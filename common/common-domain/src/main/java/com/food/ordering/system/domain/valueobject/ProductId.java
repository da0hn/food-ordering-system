package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> {

  protected ProductId(final UUID value) {
    super(value);
  }

}
