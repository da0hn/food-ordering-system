package com.food.ordering.system.payment.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
  public CreditEntryId(final UUID value) {
    super(value);
  }

  public static CreditEntryId of(final UUID value) {
    return new CreditEntryId(value);
  }
}
