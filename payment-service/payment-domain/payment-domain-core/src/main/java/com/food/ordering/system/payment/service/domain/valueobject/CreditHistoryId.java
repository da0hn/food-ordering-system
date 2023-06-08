package com.food.ordering.system.payment.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {
  public CreditHistoryId(final UUID value) {
    super(value);
  }

  public static CreditHistoryId newInstance() {
    return new CreditHistoryId(UUID.randomUUID());
  }
}
