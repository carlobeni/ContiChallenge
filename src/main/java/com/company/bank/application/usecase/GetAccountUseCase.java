package com.company.bank.application.usecase;

import com.company.bank.application.dto.AccountDto;

public interface GetAccountUseCase {
    /**
     * Obtiene la información de una cuenta bancaria a través de su ID.
     * 
     * @param accountId identificador único de la cuenta.
     * @return AccountDto con los detalles de la cuenta.
     */
    AccountDto getAccount(Long accountId);
}
