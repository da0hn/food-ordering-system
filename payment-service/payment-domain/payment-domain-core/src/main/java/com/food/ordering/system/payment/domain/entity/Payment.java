package com.food.ordering.system.payment.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.domain.valueobject.PaymentId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {

  private final OrderId orderId;
  private final CustomerId customerId;
  private final Money price;

  private PaymentStatus paymentStatus;
  private ZonedDateTime createdAt;

  private Payment(final Builder builder) {
    this.setId(builder.paymentId);
    this.orderId = builder.orderId;
    this.customerId = builder.customerId;
    this.price = builder.price;
    this.paymentStatus = builder.paymentStatus;
    this.createdAt = builder.createdAt;
  }

  public void initializePayment() {
    this.setId(new PaymentId(UUID.randomUUID()));
    this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
  }

  public void validatePayment(final Collection<? super String> failureMessages) {
    if (this.price == null || !this.price.isGreaterThanZero()) {
      failureMessages.add("Total price must be greater than zero");
    }
  }

  public void updateStatus(final PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public OrderId getOrderId() {
    return this.orderId;
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public Money getPrice() {
    return this.price;
  }

  public PaymentStatus getPaymentStatus() {
    return this.paymentStatus;
  }


  public ZonedDateTime getCreatedAt() {
    return this.createdAt;
  }


  public static final class Builder {
    private PaymentId paymentId;
    private OrderId orderId;
    private CustomerId customerId;
    private Money price;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder paymentId(final PaymentId val) {
      this.paymentId = val;
      return this;
    }

    public Builder orderId(final OrderId val) {
      this.orderId = val;
      return this;
    }

    public Builder customerId(final CustomerId val) {
      this.customerId = val;
      return this;
    }

    public Builder price(final Money val) {
      this.price = val;
      return this;
    }

    public Builder paymentStatus(final PaymentStatus val) {
      this.paymentStatus = val;
      return this;
    }

    public Builder createdAt(final ZonedDateTime val) {
      this.createdAt = val;
      return this;
    }

    public Payment build() {
      return new Payment(this);
    }
  }
}
