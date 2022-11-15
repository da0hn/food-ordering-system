package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {


  protected TrackingId(final UUID value) {
    super(value);
  }

  public static TrackingId newInstance() {
    return new TrackingId(UUID.randomUUID());
  }

  public static TrackingId of(final UUID value) {
    return new TrackingId(value);
  }

}
