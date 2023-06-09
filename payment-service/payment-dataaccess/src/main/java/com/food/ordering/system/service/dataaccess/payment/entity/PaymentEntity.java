package com.food.ordering.system.service.dataaccess.payment.entity;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 2689826113170315779L;
  @Id
  private UUID id;
  private UUID customerId;
  private UUID orderId;
  private BigDecimal price;
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;
  private ZonedDateTime createdAt;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final PaymentEntity that = (PaymentEntity) o;

    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
