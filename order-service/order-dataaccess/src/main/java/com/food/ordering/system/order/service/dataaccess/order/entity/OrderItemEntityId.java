package com.food.ordering.system.order.service.dataaccess.order.entity;

import com.sun.xml.txw2.output.SaxSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId implements Serializable {

  private Long id;
  private OrderEntity order;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final OrderItemEntityId that = (OrderItemEntityId) o;
    return this.id.equals(that.id) && this.order.equals(that.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.order);
  }

}
