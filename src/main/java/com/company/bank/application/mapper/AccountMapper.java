package com.company.bank.application.mapper;

import com.company.bank.application.dto.AccountDto;
import com.company.bank.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
    Account toEntity(AccountDto dto);
}
