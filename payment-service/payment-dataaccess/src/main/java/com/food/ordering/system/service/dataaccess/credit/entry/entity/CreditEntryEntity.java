package com.food.ordering.system.service.dataaccess.credit.entry.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_entry")
public class CreditEntryEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -7721692523535954688L;

  @Id
  private UUID id;
  private UUID customerId;
  private BigDecimal totalCreditAmount;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final CreditEntryEntity that = (CreditEntryEntity) o;

    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
