package com.food.ordering.system.payment.service.dataaccess.credit.history.entity;

import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_history")
public class CreditHistoryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -8912463834110345353L;

  @Id
  private UUID id;
  private UUID customerId;
  private BigDecimal amount;
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final CreditHistoryEntity that = (CreditHistoryEntity) o;

    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
