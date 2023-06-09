package com.food.ordering.system.payment.service.domain.ports.api.message.listener;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

  void completePayment(PaymentRequest paymentRequest);

  void cancelPayment(PaymentRequest paymentRequest);

}
