package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.valueobject.RestaurantId;

import java.util.Collections;
import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {

  private final List<Product> products;
  private final boolean active;

  private Restaurant(final Builder builder) {
    this.setId(builder.id);
    this.products = builder.products;
    this.active = builder.active;
  }

  public List<Product> getProducts() {
    return Collections.unmodifiableList(this.products);
  }

  public boolean isActive() {
    return this.active;
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {

    private RestaurantId id;
    private List<Product> products;
    private boolean active;

    private Builder() {}

    public Builder id(final RestaurantId val) {
      this.id = val;
      return this;
    }

    public Builder products(final List<Product> val) {
      this.products = val;
      return this;
    }

    public Builder active(final boolean val) {
      this.active = val;
      return this;
    }

    public Restaurant build() {
      return new Restaurant(this);
    }

  }

}
