package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

public class Money {

  private final BigDecimal amount;

  public Money(final BigDecimal amount) {
    this.amount = Money.setScale(amount);
  }

  private static BigDecimal setScale(final BigDecimal value) {
    return value.setScale(2, RoundingMode.HALF_EVEN);
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public boolean isGreaterThanZero() {
    return Optional.ofNullable(this.amount)
      .map(value -> value.compareTo(BigDecimal.ZERO) > 0)
      .orElse(false);
  }

  public boolean isGreaterThan(final Money money) {
    return Optional.ofNullable(this.amount)
      .map(value -> value.compareTo(money.amount) > 0)
      .orElse(false);
  }

  public Money add(final Money money) {
    return new Money(this.amount.add(money.amount));
  }

  public Money subtract(final Money money) {
    return new Money(this.amount.subtract(money.amount));
  }

  public Money multiply(final int multiplier) {
    return new Money(this.amount.multiply(new BigDecimal(multiplier)));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.amount);
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(o == null || this.getClass() != o.getClass()) return false;
    final Money money = (Money) o;
    return this.amount.equals(money.amount);
  }

}
