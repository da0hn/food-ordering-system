package com.food.ordering.system.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> {

  private ID id;

  protected BaseEntity(final ID id) {
    this.id = id;
  }

  protected BaseEntity() {
  }

  public ID getId() {
    return this.id;
  }

  public void setId(final ID id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(o == null || this.getClass() != o.getClass()) return false;
    final BaseEntity<?> that = (BaseEntity<?>) o;
    return this.id.equals(that.id);
  }

}
