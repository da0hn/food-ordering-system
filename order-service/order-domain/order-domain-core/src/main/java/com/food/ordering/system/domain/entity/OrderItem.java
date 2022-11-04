package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

  private final Product product;
  private final int quantity;
  private final Money price;
  private final Money subTotal;
  private OrderId orderId;

  private OrderItem(final Builder builder) {
    super(builder.id);
    this.product = builder.product;
    this.quantity = builder.quantity;
    this.price = builder.price;
    this.subTotal = builder.subTotal;
    this.orderId = builder.orderId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Product getProduct() {
    return this.product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public Money getPrice() {
    return this.price;
  }

  public Money getSubTotal() {
    return this.subTotal;
  }

  public OrderId getOrderId() {
    return this.orderId;
  }

  public void initializeOrderItem(
    final OrderId orderId,
    final OrderItemId orderItemId
  ) {
    this.orderId = orderId;
    super.setId(orderItemId);
  }

  public boolean isPriceValid() {
    return this.price.isGreaterThanZero() &&
           this.price.equals(this.product.getPrice()) &&
           this.price.multiply(this.quantity).equals(this.subTotal);
  }

  public static final class Builder {

    private OrderItemId id;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;
    private OrderId orderId;

    private Builder() {}

    public Builder id(final OrderItemId val) {
      this.id = val;
      return this;
    }

    public Builder product(final Product val) {
      this.product = val;
      return this;
    }

    public Builder quantity(final int val) {
      this.quantity = val;
      return this;
    }

    public Builder price(final Money val) {
      this.price = val;
      return this;
    }

    public Builder subTotal(final Money val) {
      this.subTotal = val;
      return this;
    }

    public Builder orderId(final OrderId val) {
      this.orderId = val;
      return this;
    }

    public OrderItem build() {
      return new OrderItem(this);
    }

  }

}
