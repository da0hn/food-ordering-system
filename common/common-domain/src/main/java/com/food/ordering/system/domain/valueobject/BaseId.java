package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<T> {

  private final T value;

  protected BaseId(final T value) {this.value = value;}

  public T getValue() {
    return this.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value);
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(o == null || this.getClass() != o.getClass()) return false;
    final BaseId<?> baseId = (BaseId<?>) o;
    return this.value.equals(baseId.value);
  }

}
