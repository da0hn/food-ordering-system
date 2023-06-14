package com.food.ordering.system.dataaccess.restaurant.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
public class RestaurantEntity {

  @Id
  private UUID restaurantId;
  @Id
  private UUID productId;

  private String restaurantName;
  private Boolean restaurantActive;
  private String productName;
  private BigDecimal productPrice;
  private Boolean productAvailable;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final RestaurantEntity that = (RestaurantEntity) o;
    return this.restaurantId.equals(that.restaurantId) && this.productId.equals(that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.restaurantId, this.productId);
  }

}
