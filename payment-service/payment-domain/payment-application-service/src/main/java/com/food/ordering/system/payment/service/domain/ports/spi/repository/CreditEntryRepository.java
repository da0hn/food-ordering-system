package com.food.ordering.system.payment.service.domain.ports.spi.repository;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

  CreditEntry save(CreditEntry creditEntry);

  Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
