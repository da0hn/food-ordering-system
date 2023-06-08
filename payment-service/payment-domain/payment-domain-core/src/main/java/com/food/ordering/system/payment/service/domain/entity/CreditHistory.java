package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

  private final CustomerId customerId;
  private final Money amount;
  private final TransactionType transactionType;

  private CreditHistory(final Builder builder) {
    this.setId(builder.creditHistoryId);
    this.customerId = builder.customerId;
    this.amount = builder.amount;
    this.transactionType = builder.transactionType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public Money getAmount() {
    return this.amount;
  }

  public TransactionType getTransactionType() {
    return this.transactionType;
  }

  public static final class Builder {
    private CreditHistoryId creditHistoryId;
    private CustomerId customerId;
    private Money amount;
    private TransactionType transactionType;

    private Builder() {
    }

    public Builder creditHistoryId(final CreditHistoryId val) {
      this.creditHistoryId = val;
      return this;
    }

    public Builder customerId(final CustomerId val) {
      this.customerId = val;
      return this;
    }

    public Builder amount(final Money val) {
      this.amount = val;
      return this;
    }

    public Builder transactionType(final TransactionType val) {
      this.transactionType = val;
      return this;
    }

    public CreditHistory build() {
      return new CreditHistory(this);
    }
  }
}
