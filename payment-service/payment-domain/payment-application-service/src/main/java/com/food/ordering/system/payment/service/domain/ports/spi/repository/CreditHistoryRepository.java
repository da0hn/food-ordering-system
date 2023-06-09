package com.food.ordering.system.payment.service.domain.ports.spi.repository;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

  CreditHistory save(CreditHistory creditHistory);

  Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);

}
