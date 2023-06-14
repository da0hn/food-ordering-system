package com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_approval", schema = "restaurant")
public class OrderApprovalEntity implements Serializable {


  @Serial
  private static final long serialVersionUID = -954683328734570744L;

  @Id
  private UUID id;
  private UUID restaurantId;
  private UUID orderId;
  @Enumerated(EnumType.STRING)
  private OrderApprovalStatus status;

}
