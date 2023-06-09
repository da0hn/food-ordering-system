package com.food.ordering.system.payment.service.dataaccess.credit.history.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.dataaccess.credit.history.entity.CreditHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDataAccessMapper {

  public CreditHistory creditHistoryEntityToCreditHistory(final CreditHistoryEntity creditHistoryEntity) {
    return CreditHistory.builder()
      .creditHistoryId(CreditHistoryId.of(creditHistoryEntity.getId()))
      .customerId(CustomerId.of(creditHistoryEntity.getCustomerId()))
      .amount(new Money(creditHistoryEntity.getAmount()))
      .transactionType(creditHistoryEntity.getType())
      .build();
  }

  public CreditHistoryEntity creditHistoryToCreditHistoryEntity(final CreditHistory creditHistory) {
    return CreditHistoryEntity.builder()
      .id(creditHistory.getId().getValue())
      .customerId(creditHistory.getCustomerId().getValue())
      .amount(creditHistory.getAmount().getAmount())
      .type(creditHistory.getTransactionType())
      .build();
  }

}
