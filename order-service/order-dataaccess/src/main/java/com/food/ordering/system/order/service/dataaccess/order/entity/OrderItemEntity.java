package com.food.ordering.system.order.service.dataaccess.order.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordem_items")
@IdClass(OrderItemEntityId.class)
public class OrderItemEntity {

  @Id
  private Long id;

  @Id
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID")
  private OrderEntity order;

  private UUID productId;
  private BigDecimal price;
  private Integer quantity;
  private BigDecimal subTotal;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final OrderItemEntity that = (OrderItemEntity) o;
    return this.id.equals(that.id) && this.order.equals(that.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.order);
  }

}
