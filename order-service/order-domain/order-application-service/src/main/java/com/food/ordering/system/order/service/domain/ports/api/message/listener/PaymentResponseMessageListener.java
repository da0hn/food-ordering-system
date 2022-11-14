package com.food.ordering.system.order.service.domain.ports.api.message.listener;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

  void paymentCompleted(PaymentResponse response);

  void paymentCancelled(PaymentResponse response);


}
