package com.food.ordering.system.payment.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
  public PaymentId(final UUID value) {
    super(value);
  }

  public static PaymentId of(final UUID id) {
    return new PaymentId(id);
  }
}
