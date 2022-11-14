package com.food.ordering.system.order.service.domain.ports.api.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

  CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

  TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);

}
