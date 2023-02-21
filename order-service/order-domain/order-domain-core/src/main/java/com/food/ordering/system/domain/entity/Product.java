package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

  private String name;
  private Money price;

  public Product(
    final ProductId productId,
    final String name,
    final Money price
  ) {
    super(productId);
    this.name = name;
    this.price = price;
  }

  private Product(final Builder builder) {
    super.setId(builder.id);
    this.name = builder.name;
    this.price = builder.price;
  }

  public static Product of(final ProductId productId) {
    return new Product(productId, null, null);
  }

  public static Product.Builder builder() {
    return new Product.Builder();
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Money getPrice() {
    return this.price;
  }

  public void setPrice(final Money price) {
    this.price = price;
  }

  public void updateWithConfirmedNameAndPrice(
    final String name,
    final Money price
  ) {
    this.name = name;
    this.price = price;
  }

  public static final class Builder {

    private ProductId id;
    private String name;
    private Money price;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    public Builder id(final ProductId id) {
      this.id = id;
      return this;
    }

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder price(final Money price) {
      this.price = price;
      return this;
    }

    public Product build() {
      return new Product(this);
    }

  }

}
