package com.food.ordering.system.domain.entity;

public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

  protected AggregateRoot(final ID id) {
    super(id);
  }

  protected AggregateRoot() {
  }

}
