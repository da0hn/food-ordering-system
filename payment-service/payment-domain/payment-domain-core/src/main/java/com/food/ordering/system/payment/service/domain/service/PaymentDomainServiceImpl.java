package com.food.ordering.system.payment.service.domain.service;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.events.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.events.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.events.PaymentEvent;
import com.food.ordering.system.payment.service.domain.events.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService {

  private final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;
  private final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;
  private final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher;

  public PaymentDomainServiceImpl(
    final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
    final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
    final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher
  ) {
    this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
    this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
  }

  private static void subtractCreditEntry(final Payment payment, final CreditEntry creditEntry) {
    creditEntry.subtractCreditAmount(payment.getPrice());
  }

  private static void validateCreditEntry(
    final Payment payment,
    final CreditEntry creditEntry,
    final List<? super String> failureMessages
  ) {
    if (payment.getPrice().isGreaterThan(creditEntry.getTotalCreditAmount())) {
      log.info(
        "Customer with id: {} doesn't have enough credit for payment!",
        payment.getCustomerId().getValue()
      );
      failureMessages.add(
        "Customer with id=" + payment.getCustomerId().getValue() + " doesn't have enough credit for payment!"
      );
    }
  }

  private static void updateCreditHistory(
    final Payment payment,
    final Collection<? super CreditHistory> creditHistories,
    final TransactionType transactionType
  ) {
    creditHistories.add(
      CreditHistory.builder()
        .creditHistoryId(CreditHistoryId.newInstance())
        .transactionType(transactionType)
        .amount(payment.getPrice())
        .build()
    );
  }

  private static void validateCreditHistory(
    final CreditEntry creditEntry,
    final List<? extends CreditHistory> creditHistories,
    final Collection<? super String> failureMessages
  ) {
    final var totalCreditHistory = getTotalHistoryAmount(creditHistories, TransactionType.CREDIT);
    final var totalDebitHistory = getTotalHistoryAmount(creditHistories, TransactionType.DEBIT);

    if (totalDebitHistory.isGreaterThan(totalCreditHistory)) {
      log.error(
        "Customer with id: {} doesn't have enough credit according to credit history",
        creditEntry.getCustomerId().getValue()
      );
      failureMessages.add(
        "Customer with id=" + creditEntry.getCustomerId().getValue() + " doesn't have enough credit according to credit history!"
      );
    }

    if (!creditEntry.getTotalCreditAmount().equals(totalCreditHistory.subtract(totalDebitHistory))) {
      log.error(
        "Credit history total is not equal to current credit for customer id: {}!",
        creditEntry.getCustomerId().getValue()
      );
      failureMessages.add("Credit history total is not equal to current credit for customer id=" + creditEntry.getCustomerId().getValue());
    }
  }

  private static Money getTotalHistoryAmount(
    final List<? extends CreditHistory> creditHistories,
    final TransactionType transactionType
  ) {
    return creditHistories.stream()
      .filter(creditHistory -> transactionType == creditHistory.getTransactionType())
      .map(CreditHistory::getAmount)
      .reduce(Money.ZERO, Money::add);
  }

  private static void addCreditEntry(final Payment payment, final CreditEntry creditEntry) {
    creditEntry.addCreditAmount(payment.getPrice());
  }

  @Override
  public PaymentEvent<?> validateAndInitiatePayment(
    final Payment payment,
    final CreditEntry creditEntry,
    final List<CreditHistory> creditHistories,
    final List<String> failureMessages
  ) {
    payment.validatePayment(failureMessages);
    payment.initializePayment();
    validateCreditEntry(payment, creditEntry, failureMessages);
    subtractCreditEntry(payment, creditEntry);
    updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
    validateCreditHistory(creditEntry, creditHistories, failureMessages);
    if (!failureMessages.isEmpty()) {
      log.info("Payment initiation is failed for order id: {}", payment.getOrderId().getValue());
      payment.updateStatus(PaymentStatus.FAILED);
      return PaymentFailedEvent.newInstance(this.paymentFailedEventDomainEventPublisher, payment, failureMessages);
    }
    log.info("Payment is initiated for order id: {}", payment.getOrderId().getValue());
    payment.updateStatus(PaymentStatus.COMPLETED);
    return PaymentCompletedEvent.newInstance(this.paymentCompletedEventDomainEventPublisher, payment);
  }

  @Override
  public PaymentEvent<?> validateAndCancelPayment(
    final Payment payment,
    final CreditEntry creditEntry,
    final List<CreditHistory> creditHistories,
    final List<String> failureMessages
  ) {
    payment.validatePayment(failureMessages);
    addCreditEntry(payment, creditEntry);
    updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

    if (!failureMessages.isEmpty()) {
      log.info("Payment is cancellation is failed for order id: {}", payment.getOrderId().getValue());
      payment.updateStatus(PaymentStatus.FAILED);
      return PaymentFailedEvent.newInstance(this.paymentFailedEventDomainEventPublisher, payment, failureMessages);
    }
    log.info("Payment is cancelled for order id: {}", payment.getOrderId().getValue());
    payment.updateStatus(PaymentStatus.CANCELLED);
    return PaymentCancelledEvent.newInstance(this.paymentCancelledEventDomainEventPublisher, payment);
  }
}
