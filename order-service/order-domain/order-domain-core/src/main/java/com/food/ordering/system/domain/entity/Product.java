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

}
