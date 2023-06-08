package com.food.ordering.system.payment.domain.service;

import com.food.ordering.system.payment.domain.entity.Payment;
import com.food.ordering.system.payment.domain.entity.CreditEntry;
import com.food.ordering.system.payment.domain.entity.CreditHistory;
import com.food.ordering.system.payment.domain.events.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

  PaymentEvent validateAndInitiatePayment(
    Payment payment,
    CreditEntry creditEntry,
    List<CreditHistory> creditHistories,
    List<String> failureMessages
  );

  PaymentEvent validateAndCancelPayment(
    Payment payment,
    CreditEntry creditEntry,
    List<CreditHistory> creditHistories,
    List<String> failureMessages
  );

}
