package com.food.ordering.system.restaurant.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {

  private final List<Product> products;
  private final OrderStatus orderStatus;
  private final Money totalAmount;

  private OrderDetail(final Builder builder) {
    this.setId(builder.orderId);
    this.orderStatus = builder.orderStatus;
    this.totalAmount = builder.totalAmount;
    this.products = builder.products;
  }

  public static Builder builder() {
    return new Builder();
  }

  public OrderStatus getOrderStatus() {
    return this.orderStatus;
  }

  public Money getTotalAmount() {
    return this.totalAmount;
  }

  public List<Product> getProducts() {
    return this.products;
  }


  public static final class Builder {
    private OrderId orderId;
    private OrderStatus orderStatus;
    private Money totalAmount;
    private List<Product> products;

    private Builder() {}

    public Builder orderId(final OrderId val) {
      this.orderId = val;
      return this;
    }

    public Builder orderStatus(final OrderStatus val) {
      this.orderStatus = val;
      return this;
    }

    public Builder totalAmount(final Money val) {
      this.totalAmount = val;
      return this;
    }

    public Builder products(final List<Product> val) {
      this.products = val;
      return this;
    }

    public OrderDetail build() {
      return new OrderDetail(this);
    }
  }
}
