package com.food.ordering.system.restaurant.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;

import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {

  private final OrderDetail orderDetail;
  private boolean active;
  private OrderApproval orderApproval;

  private Restaurant(final Builder builder) {
    this.setId(builder.restaurantId);
    this.orderApproval = builder.orderApproval;
    this.active = builder.active;
    this.orderDetail = builder.orderDetail;
  }

  public static Builder builder() {
    return new Builder();
  }

  public void validateOrder(final List<String> failureMessages) {
    if (this.orderDetail.getOrderStatus() != OrderStatus.PAID) {
      failureMessages.add("Payment is not completed for order: " + this.orderDetail.getId());
    }
    final Money totalAmount = this.orderDetail.getProducts().stream().map(product -> {
      if (!product.isAvailable()) {
        failureMessages.add("Product with id " + product.getId() + " is not available");
      }
      return product.getPrice().multiply(product.getQuantity());
    }).reduce(Money.ZERO, Money::add);

    if (totalAmount.equals(this.orderDetail.getTotalAmount())) {
      failureMessages.add("Price total is not correct for order: " + this.orderDetail.getId());
    }
  }

  public void constructOrderApproval(final OrderApprovalStatus orderApprovalStatus) {
    this.orderApproval = OrderApproval.builder()
      .orderApprovalId(OrderApprovalId.newInstance())
      .restaurantId(this.getId())
      .orderId(this.orderDetail.getId())
      .approvalStatus(orderApprovalStatus)
      .build();
  }

  public OrderApproval getOrderApproval() {
    return this.orderApproval;
  }

  public boolean isActive() {
    return this.active;
  }

  public void setActive(final boolean active) {
    this.active = active;
  }

  public OrderDetail getOrderDetail() {
    return this.orderDetail;
  }

  public static final class Builder {
    private RestaurantId restaurantId;
    private OrderApproval orderApproval;
    private boolean active;
    private OrderDetail orderDetail;

    private Builder() {}

    public Builder restaurantId(final RestaurantId val) {
      this.restaurantId = val;
      return this;
    }

    public Builder orderApproval(final OrderApproval val) {
      this.orderApproval = val;
      return this;
    }

    public Builder active(final boolean val) {
      this.active = val;
      return this;
    }

    public Builder orderDetail(final OrderDetail val) {
      this.orderDetail = val;
      return this;
    }

    public Restaurant build() {
      return new Restaurant(this);
    }
  }
}
