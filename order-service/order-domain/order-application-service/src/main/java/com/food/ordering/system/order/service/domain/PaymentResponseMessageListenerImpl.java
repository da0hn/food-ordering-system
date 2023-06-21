package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.api.message.listener.PaymentResponseMessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

  private final OrderPaymentSaga orderPaymentSaga;

  @Override
  public void paymentCompleted(final PaymentResponse response) {
    final var event = this.orderPaymentSaga.process(response);
    log.info("Publishing OrderPaidEvent for order id: {} ", response.getOrderId());
    event.fire();
  }

  @Override
  public void paymentCancelled(final PaymentResponse response) {
    final var event = this.orderPaymentSaga.rollback(response);
    log.info(
      "Order is rolled back for order id: {} with failure messages: {}",
      response.getOrderId(),
      String.join(Order.FAILURE_MESSAGE_DELIMITER, response.getFailureMessages())
    );
  }

}
