package com.company.bank.infrastructure.persistence.mapper;

import com.company.bank.domain.entity.Account;
import com.company.bank.infrastructure.persistence.entity.AccountJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountJpaMapper {
    Account toDomain(AccountJpaEntity entity);
    AccountJpaEntity toEntity(Account domain);
}
