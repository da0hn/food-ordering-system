package com.food.ordering.system.service.dataaccess.credit.entry.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.domain.entity.CreditEntry;
import com.food.ordering.system.payment.domain.valueobject.CreditEntryId;
import com.food.ordering.system.service.dataaccess.credit.entry.entity.CreditEntryEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryDataAccessMapper {

  public CreditEntry creditEntryEntityToCreditEntry(final CreditEntryEntity creditEntryEntity) {
    return CreditEntry.builder()
      .creditEntryId(CreditEntryId.of(creditEntryEntity.getId()))
      .customerId(CustomerId.of(creditEntryEntity.getCustomerId()))
      .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
      .build();
  }

  public CreditEntryEntity creditEntryToCreditEntryEntity(final CreditEntry creditEntry) {
    return CreditEntryEntity.builder()
      .id(creditEntry.getId().getValue())
      .customerId(creditEntry.getCustomerId().getValue())
      .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount())
      .build();
  }


}
