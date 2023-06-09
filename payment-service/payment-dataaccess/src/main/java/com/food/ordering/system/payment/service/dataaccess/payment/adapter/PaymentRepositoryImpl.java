package com.food.ordering.system.payment.service.dataaccess.payment.adapter;

import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.dataaccess.payment.mapper.PaymentDataAccessMapper;
import com.food.ordering.system.payment.service.dataaccess.payment.repository.PaymentJpaRepository;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository repository;
  private final PaymentDataAccessMapper mapper;

  @Override
  public Payment save(final Payment payment) {
    final var paymentEntity = this.mapper.paymentToPaymentEntity(payment);
    final var savedPaymentEntity = this.repository.save(paymentEntity);
    return this.mapper.paymentEntityToPayment(savedPaymentEntity);
  }

  @Override
  public Optional<Payment> findByOrderId(final UUID orderId) {
    return this.repository.findByOrderId(orderId)
      .map(this.mapper::paymentEntityToPayment);
  }
}
