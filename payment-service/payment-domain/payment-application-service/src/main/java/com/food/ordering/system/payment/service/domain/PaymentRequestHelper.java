package com.food.ordering.system.payment.service.domain;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.events.PaymentEvent;
import com.food.ordering.system.payment.service.domain.service.PaymentDomainService;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.exception.PaymentApplicationServiceException;
import com.food.ordering.system.payment.service.domain.mapper.PaymentDataMapper;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.CreditEntryRepository;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.CreditHistoryRepository;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentRequestHelper {

  private final PaymentDomainService paymentDomainService;
  private final PaymentDataMapper paymentDataMapper;
  private final PaymentRepository paymentRepository;
  private final CreditEntryRepository creditEntryRepository;
  private final CreditHistoryRepository creditHistoryRepository;

  @Transactional
  public PaymentEvent persistPayment(final PaymentRequest paymentRequest) {
    log.info("Received payment complete event for order id: {}", paymentRequest.getOrderId());
    final var payment = this.paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
    final var creditEntry = this.getCreditEntry(payment.getCustomerId());
    final var creditHistories = this.getCreditHistory(payment.getCustomerId());
    final var failureMessages = new ArrayList<String>();
    final var paymentEvent = this.paymentDomainService.validateAndInitiatePayment(payment, creditEntry, creditHistories, failureMessages);
    this.persistObjects(payment, creditEntry, creditHistories, failureMessages);
    return paymentEvent;
  }

  private void persistObjects(
    final Payment payment,
    final CreditEntry creditEntry,
    final List<CreditHistory> creditHistories,
    final List<String> failureMessages
  ) {
    this.paymentRepository.save(payment);
    if (failureMessages.isEmpty()) {
      this.creditEntryRepository.save(creditEntry);
      final var lastAddedCreditHistory = creditHistories.get(creditHistories.size() - 1);
      this.creditHistoryRepository.save(lastAddedCreditHistory);
    }
  }

  @Transactional
  public PaymentEvent persistCancelPayment(final PaymentRequest paymentRequest) {
    log.info("Received payment rollback event for order id: {}", paymentRequest.getOrderId());
    final var maybePayment = this.paymentRepository.findByOrderId(UUID.fromString(paymentRequest.getOrderId()));
    if (maybePayment.isEmpty()) {
      log.error("Payment with order id: {} could not be found!", paymentRequest.getOrderId());
      throw new PaymentApplicationServiceException("Payment with order id: " + paymentRequest.getOrderId() + " could not be found!");
    }
    final var payment = maybePayment.get();
    final var creditEntry = this.getCreditEntry(payment.getCustomerId());
    final var creditHistories = this.getCreditHistory(payment.getCustomerId());
    final var failureMessages = new ArrayList<String>();
    final var paymentEvent = this.paymentDomainService.validateAndCancelPayment(payment, creditEntry, creditHistories, failureMessages);
    this.persistObjects(payment, creditEntry, creditHistories, failureMessages);
    return paymentEvent;
  }

  // FIXME: apply generic method to remove redundancy
  private PaymentEvent executeDomainAction(
    final Payment payment,
    final DomainServiceAction<PaymentEvent, Payment, CreditEntry, List<CreditHistory>, List<String>> domainServiceAction
  ) {
    final var creditEntry = this.getCreditEntry(payment.getCustomerId());
    final var creditHistories = this.getCreditHistory(payment.getCustomerId());
    final var failureMessages = new ArrayList<String>();
    final var event = domainServiceAction.execute(payment, creditEntry, creditHistories, failureMessages);
    this.persistObjects(payment, creditEntry, creditHistories, failureMessages);
    return event;
  }

  private List<CreditHistory> getCreditHistory(final CustomerId customerId) {
    final var maybeCreditHistories = this.creditHistoryRepository.findByCustomerId(customerId);
    if (maybeCreditHistories.isEmpty()) {
      log.error("Could not find credit entry for customer: {}", customerId.getValue());
      throw new PaymentApplicationServiceException("Could not find credit entry for customer: " + customerId.getValue());
    }
    return maybeCreditHistories.get();
  }

  private CreditEntry getCreditEntry(final CustomerId customerId) {
    final Optional<CreditEntry> maybeCreditEntry = this.creditEntryRepository.findByCustomerId(customerId);
    if (maybeCreditEntry.isEmpty()) {
      log.error("Could not find credit history for customer: {}", customerId.getValue());
      throw new PaymentApplicationServiceException("Could not find credit history for customer: " + customerId.getValue());
    }
    return maybeCreditEntry.get();
  }

  @FunctionalInterface
  private interface DomainServiceAction<R, T, U, V, W> {
    R execute(T t, U u, V v, W w);
  }

}
