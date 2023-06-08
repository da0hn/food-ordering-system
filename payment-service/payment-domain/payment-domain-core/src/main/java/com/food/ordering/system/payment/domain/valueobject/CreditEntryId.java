package com.food.ordering.system.payment.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
  public CreditEntryId(final UUID value) {
    super(value);
  }
}
