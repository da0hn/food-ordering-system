package com.food.ordering.system.order.service.dataaccess.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_address")

public class OrderAddressEntity {

  @Id
  private UUID id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID")
  private OrderEntity order;
  private String street;
  private String postalCode;
  private String city;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final OrderAddressEntity that = (OrderAddressEntity) o;
    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

}
