package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

  public Customer() {
  }

  public Customer(final CustomerId customerId) {
    super(customerId);
  }

}
