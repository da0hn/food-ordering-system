package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntityId implements Serializable {

  @Serial private static final long serialVersionUID = 8196500703064995788L;
  @Id
  private UUID restaurantId;
  @Id
  private UUID productId;

  @Override
  public int hashCode() {
    return Objects.hash(this.restaurantId, this.productId);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final RestaurantEntityId that = (RestaurantEntityId) o;
    return this.restaurantId.equals(that.restaurantId) && this.productId.equals(that.productId);
  }

}
