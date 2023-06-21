package com.food.ordering.system.payment.service.domain;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.events.PaymentEvent;
import com.food.ordering.system.payment.service.domain.ports.api.message.listener.PaymentRequestMessageListener;
import com.food.ordering.system.payment.service.domain.ports.spi.message.publisher.PaymentCancelledMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.spi.message.publisher.PaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.domain.ports.spi.message.publisher.PaymentFailedMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

  private final PaymentRequestHelper paymentRequestHelper;

  @Override
  public void completePayment(final PaymentRequest paymentRequest) {
    final var paymentEvent = this.paymentRequestHelper.persistPayment(paymentRequest);
    this.fireEvent(paymentEvent);
  }

  private void fireEvent(final PaymentEvent<?> paymentEvent) {
    log.info(
      "Publishing payment event with payment id: {} and order id: {}",
      paymentEvent.getPayment().getId().getValue(),
      paymentEvent.getPayment().getOrderId().getValue()
    );
    paymentEvent.fire();
  }

  @Override
  public void cancelPayment(final PaymentRequest paymentRequest) {
    final var paymentEvent = this.paymentRequestHelper.persistCancelPayment(paymentRequest);
    this.fireEvent(paymentEvent);
  }
}
