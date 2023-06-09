package com.food.ordering.system.payment.service.domain;

import com.food.ordering.system.payment.domain.events.PaymentCancelledEvent;
import com.food.ordering.system.payment.domain.events.PaymentCompletedEvent;
import com.food.ordering.system.payment.domain.events.PaymentEvent;
import com.food.ordering.system.payment.domain.events.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
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
  private final PaymentCompletedMessagePublisher paymentCompletedMessagePublisher;
  private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
  private final PaymentFailedMessagePublisher paymentFailedMessagePublisher;

  @Override
  public void completePayment(final PaymentRequest paymentRequest) {
    final var paymentEvent = this.paymentRequestHelper.persistPayment(paymentRequest);
    this.fireEvent(paymentEvent);
  }

  private void fireEvent(final PaymentEvent paymentEvent) {
    log.info(
      "Publishing payment event with payment id: {} and order id: {}",
      paymentEvent.getPayment().getId().getValue(),
      paymentEvent.getPayment().getOrderId().getValue()
    );
    switch (paymentEvent) {
      case final PaymentCompletedEvent event -> this.paymentCompletedMessagePublisher.publish(event);
      case final PaymentCancelledEvent event -> this.paymentCancelledMessagePublisher.publish(event);
      case final PaymentFailedEvent event -> this.paymentFailedMessagePublisher.publish(event);
    }
  }

  @Override
  public void cancelPayment(final PaymentRequest paymentRequest) {
    final var paymentEvent = this.paymentRequestHelper.persistCancelPayment(paymentRequest);
    this.fireEvent(paymentEvent);
  }
}
